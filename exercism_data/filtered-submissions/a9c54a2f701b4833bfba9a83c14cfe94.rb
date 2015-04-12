# Determine the hamming distance between two strings

class Hamming
  def compute(strand1,strand2)

    # length of the shortest strand. No need to compare past that.
    length=(strand1.length<strand2.length ? strand1.length : strand2.length)-1

    # hamming distance
    distance=0
    
    for x in 0..length
      if strand1[x] != strand2[x]
        distance += 1
      end
    end

    return distance
  end
end
