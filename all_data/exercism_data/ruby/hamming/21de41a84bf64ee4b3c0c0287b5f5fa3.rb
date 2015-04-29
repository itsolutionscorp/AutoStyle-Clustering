class Hamming

  def self.compute(a,b)
    a.chars.zip(b.chars).take([a.length, b.length].min).select { |(a,b)| a!=b }.length end

end
