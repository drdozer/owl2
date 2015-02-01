package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait AssertionsModuleAst extends owl2.AssertionsModule {

  importedModules : owl2.AxiomsModule {
    type Assertion = ast.Assertion
  } =>

  override final type SameIndividual = ast.SameIndividual

  override final type DifferentIndividuals = ast.DifferentIndividuals

  override final type ClassAssertion = ast.ClassAssertion

  override final type ObjectPropertyAssertion = ast.ObjectPropertyAssertion

  override final type NegativeObjectPropertyAssertion = ast.NegativeObjectPropertyAssertion

  override final type DataPropertyAssertion = ast.DataPropertyAssertion

  override final type NegativeDataPropertyAssertion = ast.NegativeDataPropertyAssertion

}

case class SameIndividual(axiomAnnotations: List[Annotation],
                          individuals: List[Individual]) extends Assertion

case class DifferentIndividuals(axiomAnnotations: List[Annotation],
                                individuals: List[Individual]) extends Assertion

case class ClassAssertion(axiomAnnotations: List[Annotation],
                           individual: Individual,
                           classExpression: ClassExpression) extends Assertion

case class ObjectPropertyAssertion(axiomAnnotations: List[Annotation],
                                   sourceIndividual: Individual,
                                   objectPropertyExpression: ObjectPropertyExpression,
                                   targetIndividual: Individual) extends Assertion

case class NegativeObjectPropertyAssertion(axiomAnnotations: List[Annotation],
                                           sourceIndividual: Individual,
                                           objectPropertyExpression: ObjectPropertyExpression,
                                           targetIndividual: Individual) extends Assertion

case class DataPropertyAssertion(axiomAnnotations: List[Annotation],
                                 sourceIndividual: Individual,
                                 dataPropertyExpression: DataPropertyExpression,
                                 targetValue: Literal) extends Assertion

case class NegativeDataPropertyAssertion(axiomAnnotations: List[Annotation],
                                         sourceIndividual: Individual,
                                         dataPropertyExpression: DataPropertyExpression,
                                         targetValue: Literal) extends Assertion