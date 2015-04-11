class Hamming
  def self.compute(str1,str2)
    compute_from_pos(str1,str2,0)
  end

  def self.compute_from_pos(str1,str2,pos)
    if str1.size <= pos || str2.size <= pos
      return 0
    else
      compute_from_pos(str1,str2,pos+1) +
        if str1[pos] == str2[pos]
          0
        else
          1
        end
    end
  end
end
