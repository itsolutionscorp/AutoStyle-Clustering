class Hamming
  def initialize ; end

  def Hamming.testSingleNucleotide (c1, c2)
    if c1==c2 
      return true
    else
      return false
      end
  end

  #Calculate the Hamming distance of two DNA strands
  def Hamming.compute (s1, s2)
    distance = 0
    @testLength = 0

    #select shorter strand's length as testLength
    lengthDiff = s1.length - s2.length
    case lengthDiff
    when 0 then @testLength = s1.length
    else
        @testLength = s1.length if lengthDiff<0
        @testLength = s2.length if lengthDiff>0
    end

    for i in 0..(@testLength-1)
        bool = self.testSingleNucleotide(s1[i],s2[i])
        distance +=1 if bool == false
    end
 
    return distance
  end

end
