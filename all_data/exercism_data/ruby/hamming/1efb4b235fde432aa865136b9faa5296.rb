class Hamming

  def self.compute(s1, s2)
    s1.chars.zip(s2.chars).reduce(0) {|acc, pair| acc += 1 unless pair.first == pair.last ; acc }
  end

end
