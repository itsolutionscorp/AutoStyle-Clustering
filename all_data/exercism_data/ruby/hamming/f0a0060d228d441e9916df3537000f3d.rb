##
# The Hamming class computes simple Hamming distances.

class Hamming

  ##
  # Compute the Hamming score of the given two strings.

  def self.compute(left, right)
    score = 0
    [left.size, right.size].min.times do |i|
      score += 1 if left[i] != right[i]
    end
    score
  end

end
