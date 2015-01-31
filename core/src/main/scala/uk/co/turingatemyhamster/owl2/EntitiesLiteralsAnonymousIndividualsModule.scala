package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Entities.2C_Literals.2C_and_Anonymous_Individuals
 *
 * @author Matthew Pocock
 */
trait EntitiesLiteralsAnonymousIndividualsModule {

  importedModules : owl2.IriModule =>

  type ClassExpression

  type Entity
  // Entity: def entityIRI: IRI

  def Entity : EntityApi

  trait EntityApi {
    def unapply(entity: Entity): Some[IRI]
  }

  type DataRange

  def DataRange : DataRangeApi

  type UnlimitedNatural

  trait DataRangeApi {
    def unapply(dataRange: DataRange): Option[UnlimitedNatural]
  }

  type Individual



  type Class <: ClassExpression with Entity

  type ObjectProperty <: Entity

  type DataProperty <: Entity

  type AnnotationProperty <: Entity

  // constraint: arity == 1
  type Datatype <: Entity with DataRange

  type NamedIndividual <: Entity with Individual

  type AnonymousIndividual
  // AnonymousIndividual: def nodeId: String

  def AnonymousIndividual: AnonymousIndividualApi

  trait AnonymousIndividualApi {
    def unapply(ai: AnonymousIndividual): Some[String]
  }
}
