class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).lazy
      .map(&:uniq)
      .map(&:length)
      .map(&:pred)
      .reduce(&:+)
  end
end