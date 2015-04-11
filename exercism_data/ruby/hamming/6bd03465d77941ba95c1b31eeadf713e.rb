class Hamming < Struct.new(:dna_1, :dna_2)

  def self.compute(dna_1, dna_2)
    new(dna_1, dna_2).compute
  end

  def compute
    shortest_dna, longest_dna = [dna_1, dna_2].sort_by(&:length)
    nucleotide_pairs = shortest_dna.chars.zip(longest_dna.chars)

    count_mutations(nucleotide_pairs)
  end

  private

  def count_mutations(nucleotide_pairs)
    nucleotide_pairs.count { |first, second| first != second }
  end
end
