##
# The Hamming class computes simple Hamming distances.

class Hamming

  ##
  # Compute the Hamming score of the given two strings.

  def self.compute(left, right)
    score = 0
    size = [left.size, right.size].min
    (0..size-1).each { |i|
      score += 1 if left[i] != right[i]
    }
    score
  end

end
