class Hamming
  def compute(str1, str2)
    (0...str1.length).count { |i| str1[i] != str2[i] }
  end
end
