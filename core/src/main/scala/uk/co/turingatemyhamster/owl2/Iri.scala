package uk.co.turingatemyhamster.owl2

/**
 * An abstraction of http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#IRIs
 *
 * @author Matthew Pocock
 */
trait Iri {

  /** An IRI as defined in [http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#ref-rfc-3987 | RFC3987], enclosed in a
    * pair of < (U+3C) and > (U+3E) characters.
    */
  type fullIRI

  /** A finite sequence of characters matching the as PNAME_NS production of
    * [http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#ref-sparql | SPARQL].
    */
  type prefixName

  /** A finite sequence of characters matching the PNAME_LN production of
    * [http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#ref-sparql | SPARQL].
    */
  type abbreviatedIRI

  /** A full or abbreviated IRI.
    */
  type IRI

  /** A fullIRI is an IRI. */
  implicit def fullIRI_isa_IRI: fullIRI <:< IRI

  /** An abbreviatedIRI is an IRI. */
  implicit def abbreviatedIRI_isa_IRI: abbreviatedIRI <:< IRI
}
