class Hamming
  def compute(s1, s2)
    s1.chars
      .zip(s2.chars)
      .count { |c| c.last && c.first != c.last }
  end
end
