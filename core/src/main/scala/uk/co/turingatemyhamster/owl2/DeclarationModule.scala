package uk.co.turingatemyhamster.owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Entity_Declarations_and_Typing
 *
 * @author Matthew Pocock
 */
trait DeclarationModule {

  importedModules : OntologyModule with
    EntitiesLiteralsAnonymousIndividualsModule with
    IriModule =>

  type Declaration <: Axiom

}
