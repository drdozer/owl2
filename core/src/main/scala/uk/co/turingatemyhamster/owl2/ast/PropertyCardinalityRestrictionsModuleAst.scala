package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait ObjectPropertyCardinalityRestrictionsModuleImpl extends owl2.ObjectPropertyCardinalityRestrictionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule {
    type UnlimitedNatural = BigInt
    type ClassExpression = ast.ClassExpression
  } =>

  override final type ObjectMaxCardinality = ast.ObjectMaxCardinality

  override final type ObjectMinCardinality = ast.ObjectMinCardinality

  override final type ObjectExactCardinality = ast.ObjectExactCardinality

}

case class ObjectMaxCardinality(objectPropertyExpression: ObjectPropertyExpression,
                                classExpression: Option[ClassExpression] = None,
                                cardinality: BigInt) extends ClassExpression

case class ObjectMinCardinality(objectPropertyExpression: ObjectPropertyExpression,
                                classExpression: Option[ClassExpression] = None,
                                cardinality: BigInt) extends ClassExpression

case class ObjectExactCardinality(objectPropertyExpression: ObjectPropertyExpression,
                                  classExpression: Option[ClassExpression] = None,
                                  cardinality: BigInt) extends ClassExpression
