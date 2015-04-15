class Hamming
  
  def self.compute(str1, str2)
	str1.size.times.count { |i| str1[i] != str2[i]}
  end

end
