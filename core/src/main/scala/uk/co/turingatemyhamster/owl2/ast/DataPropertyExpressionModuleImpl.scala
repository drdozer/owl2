package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait DataPropertyExpressionModuleImpl {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule =>

  type DataPropertyExpression = ast.DataPropertyExpression

  type DataProperty = ast.DataProperty

}

sealed trait DataPropertyExpression

case class DataProperty(entityIRI: IRI) extends Entity with DataPropertyExpression