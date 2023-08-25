import viper.silicon.Config
import viper.silicon.logger.NoopSymbExLog
import viper.silicon.verifier.DefaultMainVerifier
import viper.silver.ast.{Div, Goto, Inhale, Int, IntLit, Label, LocalVar, LocalVarAssign, LocalVarDecl, Method, NeCmp, NoInfo, NoPosition, NoTrafos, Program, Seqn}
import viper.silver.reporter.NoopReporter

object Main {
  def main(args: Array[String]): Unit = {
    val labelName = "x"
    val myMethod = Method(
      "foo",
      Seq(), /* Parameters */
      Seq(), /* Returns */
      Nil, /* Preconditions */
      Nil, /* Postconditions */
      Some(Seqn(
        Seq(
          Label(labelName, Nil)(),
          Goto(labelName)(),
        ), /* Statements */
        Seq( ) /* Declarations */
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
    println(program)
    println(program.checkTransitively)
    val verifier = new DefaultMainVerifier(new Config(Nil), NoopReporter, NoopSymbExLog)
    verifier.start()
    println(verifier.verify(program, Nil, None))
  }
}