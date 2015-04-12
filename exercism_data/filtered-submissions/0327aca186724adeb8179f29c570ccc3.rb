# Computer hamm difference between two DNA strands
# Author::    Bryan Paxton  (mailto:starbelly@pobox.com)

# Hamming class
class Hamming
  def compute(seq1, seq2)
    (0...[seq1.size, seq2.size].min).count { |i| seq1[i] != seq2[i] }
  end
end
