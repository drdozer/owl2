package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait ObjectPropertyExpressionModuleImpl extends owl2.ObjectPropertyExpressionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule { type Entity = ast.Entity } =>

  override final type ObjectPropertyExpression = ast.ObjectPropertyExpression

  override final type ObjectProperty = ast.ObjectProperty

  override final type InverseObjectProperty = ast.InverseObjectProperty

}

sealed trait ObjectPropertyExpression

case class ObjectProperty(entityIRI: IRI) extends Entity with ObjectPropertyExpression

case class InverseObjectProperty(objectProperty: ObjectProperty) extends ObjectPropertyExpression