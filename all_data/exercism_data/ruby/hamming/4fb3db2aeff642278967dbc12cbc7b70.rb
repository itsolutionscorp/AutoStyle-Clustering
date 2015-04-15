class Hamming

  def self.compute(s1,s2)
  string1 = s1.split(//)
  string2 = s2.split(//)

    if string1.length == string2.length
      string1.zip(string2).count{|pair| pair[0] != pair[1]}

    elsif string1.length > string2.length
      string1[0..string2.length-1].zip(string2).count{|pair| pair[0] != pair[1]}

    else
      string2[0..string1.length-1].zip(string1).count{|pair| pair[0] != pair[1]}
    end
  end
end
