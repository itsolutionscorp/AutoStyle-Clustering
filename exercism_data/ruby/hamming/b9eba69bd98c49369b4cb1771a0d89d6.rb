class Hamming

  def self.compute(str1, str2)

    (0...(str1.length)).each.count { |x| str1[x] != str2[x] }
        
  end
 
end