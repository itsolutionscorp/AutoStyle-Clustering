class Hamming
  def Hamming.compute (s1, s2)
    if s1.size > s2.size
      Hamming.compute(s2, s1)
    else
      s1.chars.zip(s2.chars).reduce(0){|a, c| a + (c[0] == c[1] ? 0 : 1)}
    end
  end
end
