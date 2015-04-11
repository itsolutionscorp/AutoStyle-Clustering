class Hamming
  def self.compute(a, b)
    a.each_char
      .zip(b.each_char)
      .map { |a, b| b && a == b }
      .count(false)
  end
end
