package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait DataPropertyAxiomsModuleAst extends owl2.DataPropertyAxiomsModule {

  importedModules : owl2.AxiomsModule {
    type DataPropertyAxiom = ast.DataPropertyAxiom
  } =>

  override final type SubDataPropertyOf = ast.SubDataPropertyOf
  
  override final type DisjointDataProperties = ast.DisjointDataProperties
  
  override final type EquivalentDataProperties = ast.EquivalentDataProperties
  
  override final type FunctionalDataProperty = ast.FunctionalDataProperty
  
  override final type DataPropertyDomain = ast.DataPropertyDomain
  
  override final type DataPropertyRange = ast.DataPropertyRange
}

case class SubDataPropertyOf(axiomAnnotations: List[Annotation],
                             superDataPropertyExpression: DataPropertyExpression,
                             subDataPropertyExpression: DataPropertyExpression) extends DataPropertyAxiom

case class DisjointDataProperties(axiomAnnotations: List[Annotation],
                                  dataPropertyExpressions: List[DataPropertyExpression]) extends DataPropertyAxiom

case class EquivalentDataProperties(axiomAnnotations: List[Annotation],
                                    dataPropertyExpressions: List[DataPropertyExpression]) extends DataPropertyAxiom

case class FunctionalDataProperty(axiomAnnotations: List[Annotation],
                                  dataPropertyExpression: DataPropertyExpression) extends DataPropertyAxiom

case class DataPropertyDomain(axiomAnnotations: List[Annotation],
                              dataPropertyExpression: DataPropertyExpression,
                              domain: ClassExpression) extends DataPropertyAxiom

case class DataPropertyRange(axiomAnnotations: List[Annotation],
                             dataPropertyExpression: DataPropertyExpression,
                             range: DataRange) extends DataPropertyAxiom