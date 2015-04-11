class Hamming
  def self.compute(str1, str2)
    str1_chars = str1.chars
    str2_chars = str2.chars

    str1_chars.each_with_index.count { |n,i| n != str2_chars[i] }
  end
end
