class Hamming
  def compute(s1, s2)
    s1, s2 = s2, s1 if s1.size > s2.size
    s1, s2 = s1.chars, s2.chars
    s1.zip(s2).count {|(c1, c2)| c1 != c2 }
  end
end
