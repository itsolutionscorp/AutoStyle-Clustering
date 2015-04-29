class Hamming

  def self.compute(a, b)
    a.chars.zip(b.chars).count {|e| e[0] != e[1]}
  end


end
