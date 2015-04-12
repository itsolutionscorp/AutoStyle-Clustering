class Hamming
  def compute(str1, str2)
    str1.chars.zip(str2.chars).select { |c1, c2| c1 != c2 }.size
  end
end
