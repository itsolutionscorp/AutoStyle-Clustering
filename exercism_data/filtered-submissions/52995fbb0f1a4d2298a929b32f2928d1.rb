class Hamming
  def compute(d1, d2)
    d2.chars.zip(d1.chars).count { |a, b| a != b && !b.nil? }
  end
end
