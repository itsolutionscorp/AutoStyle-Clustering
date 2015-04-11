class Hamming
  def self.compute(strand1,strand2)
    count=0
    if(!(strand1.kind_of?(String) && strand2.kind_of?(String)))
      return false;
    end
    if(strand1.length!=strand2.length)
      return false;
    end
    s1_chars=strand1.split("");
    s2_chars=strand2.split("");
    s1_chars.zip(s2_chars).each do |s1_c,s2_c|
      if(c2!=c1)
        count+=1;
      end
    end
    return count;
  end
end
