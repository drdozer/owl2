package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait ObjectPropertyRestrictionModuleImpl extends owl2.ObjectPropertyRestrictionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule {
    type ClassExpression = ast.ClassExpression
  } =>

  override final type ObjectAllValuesFrom = ast.ObjectAllValuesFrom

  override final type ObjectHasSelf = ast.ObjectHasSelf

  override final type ObjectHasValue = ast.ObjectHasValue

  override final type ObjectSomeValuesFrom = ast.ObjectSomeValuesFrom

}

case class ObjectAllValuesFrom(objectPropertyExpression: ObjectPropertyExpression,
                               classExpression: ClassExpression) extends ClassExpression

case class ObjectHasSelf(objectPropertyExpression: ObjectPropertyExpression) extends ClassExpression

case class ObjectHasValue(objectPropertyExpression: ObjectPropertyExpression,
                          individual: Individual) extends ClassExpression

case class ObjectSomeValuesFrom(objectPropertyExpression: ObjectPropertyExpression,
                                classExpression: ClassExpression) extends ClassExpression