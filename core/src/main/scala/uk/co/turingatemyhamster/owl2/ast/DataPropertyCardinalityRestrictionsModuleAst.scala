package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait DataPropertyCardinalityRestrictionsModuleImpl extends owl2.DataPropertyCardinalityRestrictionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule {
    type ClassExpression = ast.ClassExpression
  } =>

  override final type DataMinCardinality = ast.DataMinCardinality

  override final type DataMaxCardinality = ast.DataMaxCardinality

  override final type DataExactCardinality = ast.DataExactCardinality

}

case class DataMinCardinality(dataPropertyExpression: DataPropertyExpression,
                              dataRange: DataRange,
                              cardinality: BigInt) extends ClassExpression

case class DataMaxCardinality(dataPropertyExpression: DataPropertyExpression,
                              dataRange: DataRange,
                              cardinality: BigInt) extends ClassExpression

case class DataExactCardinality(dataPropertyExpression: DataPropertyExpression,
                                dataRange: DataRange,
                                cardinality: BigInt) extends ClassExpression