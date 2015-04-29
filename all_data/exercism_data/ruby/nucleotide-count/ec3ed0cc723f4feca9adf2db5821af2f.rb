class DNA
  NUCLEOTIDES = 'ATCG'

  def initialize(sequence)
    raise ArgumentError unless valid_sequence? sequence
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide? nucleotide
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    Hash[NUCLEOTIDES.chars.map do |nucleotide|
      [nucleotide, count(nucleotide)]
    end]
  end

  private

  def valid_sequence?(sequence)
    sequence.chars.all? do |nucleotide|
      valid_nucleotide? nucleotide
    end
  end

  def valid_nucleotide?(nucleotide)
    NUCLEOTIDES.include? nucleotide
  end
end
