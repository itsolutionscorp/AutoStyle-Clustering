class Hamming

  def compute(s1,s2)
    diff = 0
    [s1.length,s2.length].min.times {|i| diff +=1 if s1[i] != s2[i]}
    diff
  end
end
