class DNA
  NUCLEOTIDES = %w(A T C G)

  attr_reader :strand

  def initialize strand
    raise ArgumentError if invalid_nucleotide_strand?(strand)
    @strand = strand.dup.freeze
  end

  def nucleotide_counts
    counts = new_nucleotides_count
    for nucleotide in strand.chars
      counts[nucleotide] += 1
    end

    counts
  end

  def count nucleotide
    raise ArgumentError if invalid_nucleotide_strand?(nucleotide)

    strand.count nucleotide
  end

  private

  def invalid_nucleotide_strand? strand
    strand.chars.any? do |char|
      !NUCLEOTIDES.include?(char)
    end
  end

  def new_nucleotides_count
    Hash[
      NUCLEOTIDES.map {|n| [n, 0]}
    ]
  end
end
