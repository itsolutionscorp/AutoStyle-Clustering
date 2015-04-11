class Hamming
  def self.compute(a, b)
    a = a.chars
    b = b.chars

    a.zip(b).count { |x, y| x != y }
  end

end
