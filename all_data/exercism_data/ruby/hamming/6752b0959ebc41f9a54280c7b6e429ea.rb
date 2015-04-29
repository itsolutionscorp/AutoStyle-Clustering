class Hamming
  def self.compute(first, second)
    pairs = first.chars.zip(second.chars)

    pairs.select do |pair|
      pair.first != pair.last
    end.length
  end
end
