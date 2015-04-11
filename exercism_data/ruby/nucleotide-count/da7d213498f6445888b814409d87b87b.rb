class DNA
  NUCLEOTIDES = %w(A T C G)

  attr_reader :string

  def initialize string
    raise ArgumentError if invalid_nucleotide_string?(string)
    @string = string.dup.freeze
  end

  def nucleotide_counts
    counts = new_nucleotides_count
    for nucleotide in string.chars
      counts[nucleotide] += 1
    end

    counts
  end

  def count nucleotide
    raise ArgumentError if invalid_nucleotide_string?(nucleotide)

    string.count nucleotide
  end

  private

  def invalid_nucleotide_string? string
    string.chars.any? do |char|
      !NUCLEOTIDES.include?(char)
    end
  end

  def new_nucleotides_count
    Hash[
      NUCLEOTIDES.map {|n| [n, 0]}
    ]
  end
end
