##
# The Hamming class computes simple Hamming distances.

class Hamming

  ##
  # Compute the Hamming score of the given two strings.

  def self.compute(l, r)
    [l.size, r.size].min.times.count { |i| l[i] != r[i] }
  end

end
