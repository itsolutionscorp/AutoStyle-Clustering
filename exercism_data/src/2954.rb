# Steffen Parratt (username Neffetski) 13 Nov 2014
# Exercism.io exercise 1, version 3 (after cleanup)

module Hamming

  # Return the distance between two strands a & b
  def compute (a, b)
    [a.size, b.size].min.times.count { |i| a[i] != b[i] }
  end

end
