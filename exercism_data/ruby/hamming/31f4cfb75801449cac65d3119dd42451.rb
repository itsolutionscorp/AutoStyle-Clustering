# 
# Hamming Test Class
#

class Hamming
  def self.compute(seq1,seq2)
      merged = seq1.chars.zip(seq2.chars)
      merged.count { |s1, s2| s1 != s2 }
  end
end
