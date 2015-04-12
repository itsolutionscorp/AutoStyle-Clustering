class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).count { |x| x[0] != x[1] && x[1] }
  end
end
