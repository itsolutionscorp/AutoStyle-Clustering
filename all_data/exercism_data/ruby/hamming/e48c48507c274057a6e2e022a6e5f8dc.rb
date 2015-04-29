module Hamming

  #Calculate the Hamming distance of two DNA strands
  def Hamming.compute (s1, s2)
    set = s1.chars.zip s2.chars
    distance = 0

    set.each{|a,b|
      if a!=b && b && a
        distance+=1
      end
    }
    return distance
  end

end
