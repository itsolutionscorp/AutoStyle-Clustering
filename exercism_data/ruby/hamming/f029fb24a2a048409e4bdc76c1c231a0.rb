class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).map do |pair|
      next 0 if pair[0].nil? || pair[1].nil?
      next 0 if pair[0] == pair[1]
      1
    end.reduce(&:+)
  end
end
