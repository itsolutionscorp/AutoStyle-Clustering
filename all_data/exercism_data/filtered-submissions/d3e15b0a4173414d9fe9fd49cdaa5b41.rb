class Hamming
  def Hamming.compute(s1, s2)
    raise "required same length: #{s1.length} != #{s2.length}" unless s1.length == s2.length
    s1.chars.zip(s2.chars).count { |c1, c2| c1 != c2 }
  end
end
