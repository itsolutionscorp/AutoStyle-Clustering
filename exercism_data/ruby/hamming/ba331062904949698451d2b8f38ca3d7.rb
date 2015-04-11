class Hamming
  def self.compute(first_dna_strand, second_dna_strand)
    first_dna_strand.each_char.with_index.inject(0) { |occurences, (value, index)| second_dna_strand[index].nil? || second_dna_strand[index] == value ? occurences : occurences + 1 }
  end
end
