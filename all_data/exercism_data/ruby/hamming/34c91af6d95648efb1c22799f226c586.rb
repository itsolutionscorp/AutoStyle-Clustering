# A class to compute hamming values
class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).count { |(x, y)| x != y }
  end
end
