class Hamming
  def self.compute(dna_seq1, dna_seq2)
    min_len = min(dna_seq1.length, dna_seq2.length)
    diff = 0
    for i in 0...min_len do
      diff += 1 if dna_seq1[i] != dna_seq2[i]
    end
    return diff
  end
  
  private
  def self.min(num1, num2)
    return num1 if num1 <= num2
    return num2
  end
end

puts Hamming.compute("AGG", "AAAACTGACCCACCCCAGG")
