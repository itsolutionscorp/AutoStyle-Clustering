class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).count { |a, b| !b.nil? && a != b }
  end
end
