class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).count{|ca, cb| ca != cb }
  end
end
