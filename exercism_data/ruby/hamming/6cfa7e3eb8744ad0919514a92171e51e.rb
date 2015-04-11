class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).count do |(x,y)|
      y != nil and x != y
    end
  end
end
