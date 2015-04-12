class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).count { |(first, last)| first != last }
  end
end
