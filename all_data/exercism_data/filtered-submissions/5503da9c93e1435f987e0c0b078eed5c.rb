module Hamming

  #Calculate the Hamming distance of two DNA strands
  def Hamming.compute (s1, s2)
    distance = 0
    set = s1.zip s2
    set.each{|a,b|
      if a!=b && b.nil?
        distance+=1
      end
    }
    return distance
  end

end
