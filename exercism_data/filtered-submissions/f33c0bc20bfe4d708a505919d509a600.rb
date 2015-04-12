class Hamming
  def compute(str1, str2)
    str1.chars.take(str2.length).zip(str2.chars).count{|x, y| x != y}
  end
end