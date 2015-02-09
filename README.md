# owl2

[![Join the chat at https://gitter.im/drdozer/owl2](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/drdozer/owl2?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
A scala OWL2 API

This project provides an abstract API and concrete AST for the OWL2 specification.

## Future features

- Integration with banana-rdf, to support interoperability with RDF and re-use of shared data types.
- IO: Support for reading/writing OWL/XML, RDF and functional syntax.
- Profiles: OWL2 provides multiple profiles, and this API will expose each of these in a type-safe manner.
- Symbolic Syntax: Optionally enable a mathematical DSL for constructing ontologies.
- Cross-compiliation with scala-js, to provide web-browser support for rich ontologies.
