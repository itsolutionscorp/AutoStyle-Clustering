module Hamming

  def self.compute(seq1, seq2)
    count = 0
    seq1.chars.each_with_index.count { |letter, idx|
      seq2[idx] and seq2[idx] != letter
    }
  end
end
