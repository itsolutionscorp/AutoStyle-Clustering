class Hamming
  def self.compute( a, b)
    count = 0
    pairs = a.chars.zip(b.chars)

    pairs.take(a.length > b.length ? b.length : a.length).count do | a, b|
    a != b
    end
  end
end
    
