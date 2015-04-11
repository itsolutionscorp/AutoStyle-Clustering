class Hamming
  def Hamming.compute(str1, str2)
    len=[str1.size, str2.size].min
    str1.chars.zip(str2.chars).take(len).count {|(a,b)| a != b }
  end
end
