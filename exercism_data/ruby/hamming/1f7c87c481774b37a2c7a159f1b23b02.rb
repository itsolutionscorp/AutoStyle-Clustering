class Hamming
  def Hamming.compute(s1, s2)
    raise "strings must have same length" unless s1.length == s2.length
    diff = 0
    s1.length.times { |i| diff += 1 unless s1[i] == s2[i] }
    diff
  end
end
