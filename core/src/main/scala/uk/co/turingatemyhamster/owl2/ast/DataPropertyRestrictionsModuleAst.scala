package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait DataPropertyRestrictionsModuleImpl extends owl2.DataPropertyRestrictionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule {
    type ClassExpression = ast.ClassExpression
  } =>

  override final type DataSomeValuesFrom = ast.DataSomeValuesFrom

  override final type DataAllValuesFrom = ast.DataAllValuesFrom

  override final type DataHasValue = ast.DataHasValue

}

case class DataSomeValuesFrom(dataPropertyExpression: DataPropertyExpression, arity: BigInt) extends ClassExpression

case class DataAllValuesFrom(dataPropertyExpression: DataPropertyExpression, arity: BigInt) extends ClassExpression

case class DataHasValue(dataPropertyExpression: DataPropertyExpression, literal: Literal) extends ClassExpression