class Hamming
  def self.compute(x,y)
    x.chars.map.with_index{|n,i| n != y.chars[i]}.count(true)
  end
end
