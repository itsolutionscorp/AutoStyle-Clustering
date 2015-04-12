# Steffen Parratt (username Neffetski) 13 Nov 2014
# Exercism.io exercise 1, version 2 (after reviewing submissions by others)

module Hamming

  def compute (strand_a, strand_b)
    # compute and return the hamming distance between two strands
    [strand_a.size, strand_b.size].min.times.count { |i| strand_a[i] != strand_b[i] }
  end

end
