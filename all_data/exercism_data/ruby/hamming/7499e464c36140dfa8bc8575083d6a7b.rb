class Hamming
  def self.compute a, b
    a.chars.zip(b.chars).select{ |p| p[0] != p[1] }.size
  end
end
