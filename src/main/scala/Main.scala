import viper.silicon.Config
import viper.silicon.logger.NoopSymbExLog
import viper.silicon.verifier.DefaultMainVerifier
import viper.silver.ast.{Div, Inhale, Int, IntLit, LocalVar, LocalVarAssign, LocalVarDecl, Method, NeCmp, NoInfo, NoPosition, NoTrafos, Program, Seqn}
import viper.silver.reporter.NoopReporter

object Main {
  def main(args: Array[String]): Unit = {
    val xName = "local$x"
    val yName = "local$y"
    val retName = "ret$"
    val x = LocalVar(xName, Int)()
    val myMethod = Method(
      "global$pkg_$division",
      Seq(LocalVarDecl(xName, Int)()), /* Parameters */
      Seq(LocalVarDecl(retName, Int)()), /* Returns */
      Nil, /* Preconditions */
      Nil, /* Postconditions */
      Some(Seqn(
        Seq(
          Inhale(NeCmp(x, IntLit(0)())())(),
          LocalVarAssign(LocalVar(yName, Int)(), Div(x, x)())()
        ), /* Statements */
        Seq(
          LocalVarDecl(yName, Int)()
        ) /* Declarations */
      )())
    )()
    val program = Program(
      Nil, /* Domain */
      Nil, /* Field */
      Nil, /* Function */
      Nil, /* Predicate */
      Seq(myMethod), /* Method */
      Nil, /* Extension */
    )()
    val verifier = new DefaultMainVerifier(new Config(Nil), NoopReporter, NoopSymbExLog)
    verifier.start()
    verifier.verify(program, Nil, None)
  }
}