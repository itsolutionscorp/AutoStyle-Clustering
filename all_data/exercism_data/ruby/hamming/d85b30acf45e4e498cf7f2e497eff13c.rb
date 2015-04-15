class Hamming
  def self.compute(dna_seq_1, dna_seq_2)
    diff = 0
    dna_seq_1.each_char.with_index { |e, i| diff += 1 if e != dna_seq_2[i] }
    diff
  end
end
