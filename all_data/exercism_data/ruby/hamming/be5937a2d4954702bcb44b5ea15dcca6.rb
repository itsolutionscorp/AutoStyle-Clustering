class Hamming
  def self.compute(a, b)
    arrange(a,b).count{|achar, bchar| achar != bchar }
  end
  
  def self.arrange(a, b)
    a.chars.zip(b.chars)
  end
end
