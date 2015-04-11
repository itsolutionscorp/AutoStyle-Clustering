class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).count { |a, b| a != b && !b.nil? }
  end
end
