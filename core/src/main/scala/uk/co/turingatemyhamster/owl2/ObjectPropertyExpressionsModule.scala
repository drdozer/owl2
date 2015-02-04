package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Object_Property_Expressions
 *
 * @author Matthew Pocock
 */
trait ObjectPropertyExpressionsModule {

  importedModules : owl2.IriModule with owl2.EntitiesLiteralsAnonymousIndividualsModule =>

  type ObjectPropertyExpression <: AnyRef

  type ObjectProperty <: Entity with ObjectPropertyExpression

  type InverseObjectProperty <: ObjectPropertyExpression

}
