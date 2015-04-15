module Hamming
  def self.compute(a,b)
    a.chars.zip(b.chars).count{|p| p[0] != p[1]}
  end
end
