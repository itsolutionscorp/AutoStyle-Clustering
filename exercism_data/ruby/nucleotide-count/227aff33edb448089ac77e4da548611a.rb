class DNA
  NUCLEOTIDES = %w[A T C G U].freeze
  DNA_NUCLEOTIDES = %w[A T C G].freeze

  def initialize dna
    @dna = dna
  end

  def count nucleotide
    raise ArgumentError unless NUCLEOTIDES.include? nucleotide

    @dna.count nucleotide
  end

  def nucleotide_counts
    counts = Hash[DNA_NUCLEOTIDES.map { |n| [n, 0] }]

    @dna.each_char.with_object(counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end
end
