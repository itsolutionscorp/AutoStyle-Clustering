class DNA
  DNA_NUCLEOTIDES = %w(A C G T)
  RNA_NUCLEOTIDES = %w(A C G U)
  VALID_NUCLEOTIDES = DNA_NUCLEOTIDES | RNA_NUCLEOTIDES
  NOT_DNA_REGEX = /[^#{DNA_NUCLEOTIDES.join}]/

  def initialize sequence
    raise ArgumentError if sequence.match NOT_DNA_REGEX
    @sequence = sequence
    @empty_count = Hash.new(0)
    DNA_NUCLEOTIDES.each do |nucleotide|
      @empty_count[nucleotide] = 0
    end
  end

  def count nucleotide
    raise ArgumentError unless VALID_NUCLEOTIDES.include? nucleotide
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @nucleotide_counts ||= sequence.split("").each.with_object empty_count do |nucleotide, count|
      count[nucleotide] += 1
    end
  end

  private

  attr_reader :sequence, :empty_count
end
