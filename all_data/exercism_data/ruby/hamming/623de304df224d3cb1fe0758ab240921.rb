class Hamming
  attr_reader :dna_1, :dna_2

  def self.compute(dna_1, dna_2)
    new(dna_1, dna_2).compute
  end

  def initialize(dna_1, dna_2)
    min_length = [dna_1.length, dna_2.length].min

    @dna_1 = normalize_length(dna_1, min_length)
    @dna_2 = normalize_length(dna_2, min_length)
  end

  def compute
    nucleotide_pairs = dna_1.chars.zip(dna_2.chars)
    count_mutations(nucleotide_pairs)
  end

  private

  def normalize_length(dna, length)
    dna[0..length-1]
  end

  def count_mutations(nucleotide_pairs)
    nucleotide_pairs.count { |pair| pair[0] != pair[1] }
  end
end
