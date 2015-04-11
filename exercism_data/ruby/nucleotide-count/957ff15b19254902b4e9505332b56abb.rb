module Nucleotids
  ADENINE  = 'A'
  GUANINE  = 'G'
  CYTIDINE = 'C'
  THYMINE  = 'T'
  URACIL   = 'U'

  MUTUAL_NUCLEOTIDS = [ADENINE, GUANINE, CYTIDINE]
  DNA_NUCLEOTIDS    = MUTUAL_NUCLEOTIDS + [THYMINE]
  RNA_NUCLEOTIDS    = MUTUAL_NUCLEOTIDS + [URACIL]

  def belongs_to_dna(nucleotide)
    DNA_NUCLEOTIDS.include? nucleotide
  end

  def belongs_to_rna(nucleotide)
    RNA_NUCLEOTIDS.include? nucleotide
  end
end

class DNA
  include Nucleotids

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless belongs_to_dna(nucleotide) || belongs_to_rna(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    { ADENINE  => count(ADENINE),
      GUANINE  => count(GUANINE),
      CYTIDINE => count(CYTIDINE),
      THYMINE  => count(THYMINE)
    }
  end
end
