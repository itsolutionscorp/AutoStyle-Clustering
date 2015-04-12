##
# The Hamming class computes simple Hamming distances.

class Hamming

  ##
  # Compute the Hamming score of the given two strings.

  def compute(left, right)

    # return score of 0 when both strands are identical
    if left == right
     return 0
    end

    score = 0
    (0..left.size-1).each { |i|

      # stop scoring if we reached the end of the right strand
      if i > right.size-1
        break
      end

      # increment score if elements at the same index in left and right are not equal
      if left[i] != right[i]
        score += 1
      end
    }
    score

  end
  
end
