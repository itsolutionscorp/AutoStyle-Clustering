class DNA
  def initialize(sequence)
    sequence.chars { |s| raise ArgumentError unless dna.include?(s) }
    @sequence = sequence
  end

  def count(nucleotide)
    raise(ArgumentError, "#count") unless dna.include?(nucleotide) || rna.include?(nucleotide)
    sequence.scan(nucleotide).size
  end

  def nucleotide_counts
    hash = {}
    dna.map { |n| hash[n] = count(n) }
    hash
  end

  private

  attr_reader :sequence

  def dna
    ["A", "T", "C", "G"]
  end

  def rna
    ["U"]
  end
end
