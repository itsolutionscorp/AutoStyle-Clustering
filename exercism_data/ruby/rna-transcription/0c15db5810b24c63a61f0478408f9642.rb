class NucleotideSequence
  ADENINE   = 'A'.freeze
  CYTOSINE  = 'C'.freeze
  GUANINE   = 'G'.freeze
  THYMIDINE = 'T'.freeze
  URACIL    = 'U'.freeze

  attr_reader :sequence

  def self.from_string(string)
    new(string.upcase.chars)
  end

  def initialize(sequence)
    @sequence = sequence
  end
end

class DnaSequence < NucleotideSequence
  RNA_NUCLEOTIDE_PAIRS = [[GUANINE,   CYTOSINE],
                          [CYTOSINE,  GUANINE],
                          [THYMIDINE, ADENINE],
                          [ADENINE,   URACIL]].to_h

  def rna_complement
    sequence.map { |nucleotide| RNA_NUCLEOTIDE_PAIRS[nucleotide] }.join
  end
end

class RnaSequence < NucleotideSequence
  DNA_NUCLEOTIDE_PAIRS = [[GUANINE,   CYTOSINE],
                          [CYTOSINE,  GUANINE],
                          [ADENINE,   THYMIDINE],
                          [URACIL,    ADENINE]].to_h

  def dna_complement
    sequence.map { |nucleotide| DNA_NUCLEOTIDE_PAIRS[nucleotide] }.join
  end
end

module Complement
  def self.of_dna(string)
    DnaSequence.from_string(string).rna_complement
  end

  def self.of_rna(string)
    RnaSequence.from_string(string).dna_complement
  end
end
