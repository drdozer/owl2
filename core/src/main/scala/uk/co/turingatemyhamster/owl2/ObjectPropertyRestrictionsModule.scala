package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Object_Property_Restrictions
 *
 * @author Matthew Pocock
 */
trait ObjectPropertyRestrictionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule =>

  type ObjectAllValuesFrom <: ClassExpression

  type ObjectHasSelf <: ClassExpression

  type ObjectHasValue <: ClassExpression

  type ObjectSomeValuesFrom <: ClassExpression

}
