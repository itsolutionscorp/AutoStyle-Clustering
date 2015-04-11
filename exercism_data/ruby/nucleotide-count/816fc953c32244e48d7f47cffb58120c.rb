class DNA
  NUCLEOTIDES = %w[A T C G U].freeze
  DNA_NUCLEOTIDES = %w[A T C G].freeze

  def initialize dna_sequence
    @dna_sequence = dna_sequence
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each.with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count nucleotide
    end
  end

  def count nucleotide
    if NUCLEOTIDES.include? nucleotide
      @dna_sequence.count nucleotide
    else
      raise ArgumentError, 'Not a valid nucleotide'
    end
  end
end
