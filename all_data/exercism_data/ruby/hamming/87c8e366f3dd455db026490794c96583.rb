class Hamming
  def self.compute(a, b)
    sample_a = a.chars
    sample_b = b.chars
    zipped = sample_a.zip(sample_b)
    count = zipped.count { |el_a, el_b| el_b.nil? ? false : el_a != el_b }
    count
  end
end
