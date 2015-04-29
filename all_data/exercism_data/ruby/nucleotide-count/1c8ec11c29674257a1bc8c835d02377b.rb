module Nucleotids
  MUTUAL_NUCLEOTIDS = { ADENINE:  'A', GUANINE:  'G', CYTIDINE: 'C' }
  DNA_NUCLEOTIDS    = MUTUAL_NUCLEOTIDS.merge({ THYMINE: 'T' })
  RNA_NUCLEOTIDS    = MUTUAL_NUCLEOTIDS.merge({ URACIL:  'U' })

  def valid?(nucleotide)
    DNA_NUCLEOTIDS.merge(RNA_NUCLEOTIDS).values.include? nucleotide
  end
end

class DNA
  include Nucleotids

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless valid?(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    Hash[DNA_NUCLEOTIDS.values.map { |nucleotide| [nucleotide, count(nucleotide)] }]
  end
end
