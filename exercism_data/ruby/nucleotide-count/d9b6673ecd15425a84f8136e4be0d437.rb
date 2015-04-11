class DNA
  NUCLEOTIDES     = %w(A T C G U)
  DNA_NUCLEOTIDES = %w(A T C G)

  def initialize(string)
    @string = string.chars
    validate_string!
  end

  def count(nucleotide)
    raise(ArgumentError) unless NUCLEOTIDES.include?(nucleotide)

    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    @nucleotide_counts ||= @string.each_with_object(base_counts) do |nucleotide, counts|
      counts[nucleotide] = counts[nucleotide] + 1
    end
  end

  private

  def base_counts
    Hash[DNA_NUCLEOTIDES.map { |nucleotide| [nucleotide, 0] }]
  end

  def validate_string!
    (@string.uniq - DNA_NUCLEOTIDES).empty? || raise(ArgumentError)
  end
end
