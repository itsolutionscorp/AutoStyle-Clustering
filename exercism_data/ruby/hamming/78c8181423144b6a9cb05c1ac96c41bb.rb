class Hamming < Struct.new(:dna_1, :dna_2)

  def self.compute(dna_1, dna_2)
    new(dna_1, dna_2).compute
  end

  def compute
    shortest_dna, longest_dna = [dna_1.chars, dna_2.chars].sort_by(&:length)
    nucleotide_pairs = shortest_dna.zip(longest_dna)

    count_mutations(nucleotide_pairs)
  end

  private

  def count_mutations(nucleotide_pairs)
    nucleotide_pairs.count { |pair| pair[0] != pair[1] }
  end
end
