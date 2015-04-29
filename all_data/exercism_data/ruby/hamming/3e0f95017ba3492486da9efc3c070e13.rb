class Hamming

  def self.compute(str1, str2)
    str1.chars.zip(str2.chars).count do |v| 
      v[0] != v[1] && v[1]
    end
  end
  
end
