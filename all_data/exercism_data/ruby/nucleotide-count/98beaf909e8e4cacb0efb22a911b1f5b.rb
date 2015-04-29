class DNA
  def initialize(sequence)
    @sequence = sequence if valid?(sequence)
    @hash = {}
  end

  def count(nucleotide)
    raise ArgumentError unless valid_dna?(nucleotide) || valid_rna?(nucleotide)
    sequence.scan(nucleotide).size
  end

  def nucleotide_counts
    dna.map { |n| hash[n] = count(n) }
    hash
  end

  private

  attr_reader :sequence
  attr_accessor :hash

  def valid?(sequence)
    sequence.chars do |nucleotide|
      raise ArgumentError unless valid_dna?(nucleotide)
    end
  end

  def dna
    ["A", "T", "C", "G"]
  end

  def valid_dna?(nucleotide)
    dna.include?(nucleotide)
  end

  def rna
    ["U"]
  end

  def valid_rna?(nucleotide)
    rna.include?(nucleotide)
  end
end
