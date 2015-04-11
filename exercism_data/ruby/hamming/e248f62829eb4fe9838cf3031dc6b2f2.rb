class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).select{ |a1, b1| a1 && b1 && a1 != b1 }.size
  end
end
