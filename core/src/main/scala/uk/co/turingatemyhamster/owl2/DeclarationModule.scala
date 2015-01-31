package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Entity_Declarations_and_Typing
 *
 * @author Matthew Pocock
 */
trait DeclarationModule {

  importedModules : owl2.OntologyModule with
    owl2.IriModule =>

  type Declaration <: Axiom

}
