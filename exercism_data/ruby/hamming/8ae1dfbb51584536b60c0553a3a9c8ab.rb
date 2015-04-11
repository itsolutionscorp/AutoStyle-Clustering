class Hamming

  def self.compute(str1, str2)
    str1.chars.each_with_index.count { |x, i| x != str2[i] }
  end
end
