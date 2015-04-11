class DNA

  NUCLEOTIDES = %w(A T C G U)
  DNA_NUCLEOTIDES = %w(A T C G)

  def initialize(dna)
    @dna = dna
    raise ArgumentError unless given_valid_dna?
  end

  def count(nucleotide)
    raise ArgumentError unless is_valid_dna_nucleotide?(nucleotide)
    nucleotide_counts[nucleotide].to_i
  end

  def nucleotide_counts
    Hash[*array_of_nucleotides_and_counts]
  end

  private
  def array_of_nucleotides_and_counts
    DNA_NUCLEOTIDES.map do |nucleotide|
      [nucleotide, @dna.chars.count(nucleotide)]
    end.flatten
  end

  private
  def given_valid_dna?
    (@dna.chars.to_a-DNA_NUCLEOTIDES).empty?
  end

  private
  def is_valid_dna_nucleotide?(nucleotide)
    NUCLEOTIDES.include?(nucleotide)
  end
end
