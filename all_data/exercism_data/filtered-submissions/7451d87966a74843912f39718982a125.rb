class Hamming
  def Hamming.compute(s1, s2)
    raise "strings must have same length" unless s1.length == s2.length
    s1.chars.zip(s2.chars).count { |c| c[0] != c[1] }
  end
end
