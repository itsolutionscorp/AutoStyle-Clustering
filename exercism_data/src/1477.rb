class Hamming
  def compute(str1, str2)
    str1.chars.zip(str2.chars).count { |char1, char2| char1 != char2 }
  end
end
