module Hamming

  def self.compute(seq1, seq2)
    seq1.chars.zip(seq2.chars).count { |base1, base2|
      base2 and base1 != base2
    }
  end
end
