require 'pry'

class Hamming
  def compute(strand1, strand2)
    count = strand1.length - 1
    differences = 0
    if strand1 == strand2
      differences
    else
      until count == -1
        differences += 1 if strand1[count] != strand2[count]
        count -= 1
      end
      differences
    end
  end
end
