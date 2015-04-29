class Nucleotide
  def self.from_dna(strand)
    new(strand)
  end

  def initialize(strand)
    @strand = strand
    @histogram = empty_histogram
    count_nucleotides
  end

  attr_reader :strand, :histogram

  def count(nucleotide)
    histogram[nucleotide]
  end

  private

  VALID_NUCLEOTIDES = %w(A T C G)

  def count_nucleotides
    strand.each_char do |nucleotide|
      histogram[nucleotide] += 1
    end
  end

  def empty_histogram
    histogram =
      Hash.new do |_, nucleotide|
        raise ArgumentError, "#{nucleotide} is not a valid nucleotide"
      end
    VALID_NUCLEOTIDES.each do |nucleotide|
      histogram[nucleotide] = 0
    end
    histogram
  end
end
