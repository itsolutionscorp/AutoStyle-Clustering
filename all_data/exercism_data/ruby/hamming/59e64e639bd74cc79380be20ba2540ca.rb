class Hamming
  def self.compute(first_dna_strand, second_dna_strand)
    first_dna_strand.chars.each_with_index.inject(0) do |memo, (nucleotide, index)|
      nucleotide == second_dna_strand.chars[index] ? memo : memo += 1
    end
  end
end
