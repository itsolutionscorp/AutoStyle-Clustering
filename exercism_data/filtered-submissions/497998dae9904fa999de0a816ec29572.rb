class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).map{ |x,y| (x <=> y).abs }.reduce(&:+)
  end
end
