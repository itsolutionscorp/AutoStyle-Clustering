class Hamming
  def compute(str1, str2)
    str1.chars.zip(str2.chars).count { |e| e[1] && e[0] != e[1] }
  end
end
