class Hamming
  def self.compute(str1, str2)
    common_length = [str1.length,str2.length].min
    common_length.times.count { |i| str1[i] != str2[i]}
  end

  def self.compute1(str1, str2)
    [str1.length,str2.length].min.times.count { |i| str1[i] != str2[i]}
  end
end
