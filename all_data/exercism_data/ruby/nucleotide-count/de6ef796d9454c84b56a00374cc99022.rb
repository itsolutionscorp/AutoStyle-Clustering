module Nucleotides
  GUANINE   = 'G'
  ADENINE   = 'A'
  THYMINE   = 'T'
  CYTOSINE  = 'C'
  URACIL    = 'U'

  def dna_nucleotides
    [ADENINE,THYMINE,CYTOSINE,GUANINE]
  end

  def all_nucleotides
    dna_nucleotides + [URACIL]
  end
end

class DNA < Struct.new(:sequence)
  include Nucleotides

  def nucleotide_counts
    dna_nucleotides.inject({}) do |result, nucleotide|
      result[nucleotide] = count nucleotide
      result
    end
  end

  def count nucleotide
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide." \
    unless all_nucleotides.include? nucleotide
    sequence.count nucleotide
  end
end
