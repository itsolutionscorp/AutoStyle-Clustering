class DNA
  VALID_NUCLEOTIDES = %w(A C G T U)

  attr_reader :nucleotides

  def initialize(sequence)
    @nucleotides = sequence.chars
    validate_nucleotides
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    nucleotide_count_tuples = ['A', 'C', 'G', 'T'].map do |nucleotide|
      [nucleotide, count(nucleotide)]
    end
    Hash[nucleotide_count_tuples]
  end

  private

  def validate_nucleotides
    unless (nucleotides.to_a - VALID_NUCLEOTIDES).empty?
      raise ArgumentError
    end
  end

  def validate_nucleotide(nucleotide)
    unless VALID_NUCLEOTIDES.include?(nucleotide)
      raise ArgumentError
    end
  end
end
