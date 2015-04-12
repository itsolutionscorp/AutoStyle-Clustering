class Hamming
  def compute(s1, s2)
    s1,s2 = s2,s1 if s1.length > s2.length
    s1.length.times.count do |x|
      s1[x] != s2[x]
    end
  end
end
