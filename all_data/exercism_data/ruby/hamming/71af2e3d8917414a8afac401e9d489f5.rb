class Hamming

  def self.compute(a, b)
    a.chars.zip(b.chars).count{|c| c[0] != c[1]}
  end

end
