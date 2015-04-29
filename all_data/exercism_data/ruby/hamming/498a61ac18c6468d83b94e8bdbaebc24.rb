class Hamming

  def self.compute(a,b)
    a,b = b,a if b.length < a.length
    a.chars.zip(b.chars).count { |(x,y)| x!=y }
  end

end
