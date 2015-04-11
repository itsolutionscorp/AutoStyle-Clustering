class Hamming

  def self.compute(a,b)
    a.chars.zip(b.chars).select { |e| bases_mismatch?(e) }.length
  end

  def self.bases_mismatch?(e)
    e[0] != e[1] and e[0] and e[1]
  end

end
