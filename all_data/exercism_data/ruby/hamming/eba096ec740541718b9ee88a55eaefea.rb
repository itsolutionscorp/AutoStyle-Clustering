class Hamming
  def self.compute(a, b)
    arrange_strands(a,b).count{|achar, bchar| achar != bchar }
  end
  
  def self.arrange_strands(a, b)
    a.chars.zip(b.chars)
  end
end
