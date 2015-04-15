class Hamming
  def self.compute(a, b)
    point_pairs(a, b).count { |pa, pb| pa != pb }
  end

  def self.point_pairs(a, b)
    a.chars.zip(b.chars)
  end
end
