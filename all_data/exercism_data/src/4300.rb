class Hamming
  def compute(s1, s2)
    s1.chars
      .zip(s2.chars)
      .select {|pair| pair.all? }
      .select {|x, y| x != y }
      .count
  end
end
