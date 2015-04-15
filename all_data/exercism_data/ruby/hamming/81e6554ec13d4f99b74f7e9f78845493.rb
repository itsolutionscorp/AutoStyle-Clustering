class Hamming
  def self.compute(a, b)
    arranged_strands(a,b).count{|achar, bchar| achar != bchar }
  end
  
  def self.arranged_strands(a, b)
    a.chars.zip(b.chars)
  end
end
