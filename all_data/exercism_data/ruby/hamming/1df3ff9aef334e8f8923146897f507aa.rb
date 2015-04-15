class Hamming 
  def self.compute(str1, str2)
    str1.chars.zip(str2.chars).count do |(ch1, ch2)| 
      (ch1 != ch2) && ch2
    end
  end
end
