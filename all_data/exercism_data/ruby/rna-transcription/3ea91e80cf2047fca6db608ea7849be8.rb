# Constants for biological molecules
module Nucleotide
  THYMINE = 'T'
  URACIL = 'U'
end

# A nucleotide sequence, comparable with Strings
class NucleicAcid
  extend Nucleotide
  def initialize(sequence)
    @sequence = sequence.to_str
  end

  attr_reader :sequence
  alias_method :to_str, :sequence

  def ==(other)
    equal?(other) || other == @sequence
  end

  protected

  def transcribe(from_nuc, to_nuc)
    @sequence.tr(from_nuc, to_nuc)
  end
end

# A sequence constructed from the alphabet A,T,G,C.
class DNA < NucleicAcid
  def to_rna
    RNA.new(to_rna_string)
  end

  def to_rna_string
    transcribe(THYMINE, URACIL)
  end
end

# A sequence constructed from the alphabet A,U,G,C.
class RNA < NucleicAcid
  def to_dna
    DNA.new(to_dna_string)
  end

  def to_dna_string
    transcribe(URACIL, THYMINE)
  end
end
