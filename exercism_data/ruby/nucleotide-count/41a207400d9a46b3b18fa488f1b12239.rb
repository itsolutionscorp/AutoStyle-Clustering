class DNA
  NUCLEOTIDES = %w[A T C G U].freeze
  DNA_NUCLEOTIDES = %w[A T C G].freeze

  def initialize sequence
    @sequence = sequence
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each.with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count nucleotide
    end
  end

  def count nucleotide
    raise ArgumentError, 'Not a valid nucleotide' unless nucleotide? nucleotide
    @sequence.count nucleotide
  end

  private
  def nucleotide? nucleotide
    NUCLEOTIDES.include? nucleotide
  end
end
