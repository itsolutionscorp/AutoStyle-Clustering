class Nucleotide
  attr_reader :histogram

  def self.from_dna(strand)
    new(strand)
  end

  def initialize(strand)
    @strand = strand
    fail ArgumentError unless valid?

    build_histogram
  end

  def count(nucleotide)
    @histogram[nucleotide]
  end

  private

  def valid?
    @strand.tr("ATCG", '').empty?
  end

  def build_histogram
    initialize_histogram

    @strand.chars.each_with_object(@histogram) do |nucleotide, histogram|
      histogram[nucleotide] += 1
    end
  end

  def initialize_histogram
    @histogram = %w(A T C G).each_with_object({}) do |nucleotide, histogram|
      histogram[nucleotide] = 0
    end
  end
end
