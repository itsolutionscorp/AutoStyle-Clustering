class Hamming
  def self.compute(s1, s2)
    char_pairs(s1, s2).reduce(0) do |dist, (c1, c2)|
      dist + (c1 == c2 ? 0 : 1)
    end
  end

  def self.char_pairs(s1, s2)
    s1.slice(0, s2.length).chars.zip(s2.chars)
  end
end
