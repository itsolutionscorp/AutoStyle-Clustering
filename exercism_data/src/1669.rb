class Hamming
  def compute(s, t)
    s.each_char.zip(t.each_char).select {|s1,t1| t1 && s1 != t1}.length
  end
end
