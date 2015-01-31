package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait EntitiesLiteralsAnonymousIndividualsModuleImpl extends owl2.EntitiesLiteralsAnonymousIndividualsModule {

  importedModules : owl2.IriModule { type IRI = ast.IRI } =>

  override final type ClassExpression = ast.ClassExpression

  override final type Entity = ast.Entity

  override final val Entity: EntityApi = new EntityApi {
    override def unapply(entity: Entity) = Some(entity.entityIRI)
  }

  override final type DataRange = ast.DataRange[UnlimitedNatural]

  override final type Individual = ast.Individual

  override final type Datatype = ast.Datatype[UnlimitedNatural]

  override final type ObjectProperty = ast.ObjectProperty

  override final type DataProperty = ast.DataProperty

  override final type AnnotationProperty = ast.AnnotationProperty

  override final type NamedIndividual = ast.NamedIndividual

  override final type AnonymousIndividual = ast.AnonymousIndividual
}

trait ClassExpression

trait Entity {
  def entityIRI: IRI
}

trait DataRange[UnlimitedNatural] {
  def arity: UnlimitedNatural
}

trait Individual



case class Class(entityIRI: IRI) extends ClassExpression with Entity

// rdfs:Literal or a datatype in the datatype map or not a reserved vocabulary IRI
case class Datatype[UnlimitedNatural](entityIRI: IRI,
                                      arity: UnlimitedNatural,
                                      datatype: Literal) extends Entity with DataRange[UnlimitedNatural]

case class ObjectProperty(entityIRI: IRI) extends Entity

case class DataProperty(entityIRI: IRI) extends Entity

case class AnnotationProperty(entityIRI: IRI) extends Entity

case class NamedIndividual(entityIRI: IRI) extends Entity with Individual

case class AnonymousIndividual(nodeID: String) extends Individual

sealed trait Literal

case class TypedLiteral[UnlimitedNatural](value: String, datatype: Datatype[UnlimitedNatural]) extends Literal
case class StringLiteralNoLangauge[UnlimitedNatural](value: String) extends Literal
case class StringLiteralWithLanguage[UnlimitedNatural](value: String, language: String) extends Literal