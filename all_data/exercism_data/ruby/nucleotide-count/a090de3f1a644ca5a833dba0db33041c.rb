module Nucleotides
  GUANINE   = 'G'
  ADENINE   = 'A'
  THYMINE   = 'T'
  CYTOSINE  = 'C'
  URACIL    = 'U'

  def Nucleotides.dna
    [ADENINE, THYMINE, CYTOSINE, GUANINE]
  end

  def Nucleotides.rna
    [ADENINE, CYTOSINE, GUANINE, URACIL]
  end

  def Nucleotides.all
    (dna + rna).uniq
  end

  def Nucleotides.valid? nucleotide
    all.include?(nucleotide) ? true : raise_error(nucleotide)
  end

  private

  def Nucleotides.raise_error nucleotide
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide."
  end
end

class DNA
  require 'attr_extras'

  pattr_initialize :sequence

  def nucleotide_counts
    Nucleotides.dna.inject({}) do |result, nucleotide|
      result[nucleotide] = count nucleotide
      result
    end
  end

  def count nucleotide
    sequence.count nucleotide if Nucleotides.valid? nucleotide
  end
end
