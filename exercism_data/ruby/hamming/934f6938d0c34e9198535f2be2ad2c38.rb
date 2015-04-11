class Hamming
  def self.compute(first, second)
    first.chars.zip(second.chars).count do |pair|
      pair.compact == pair && pair[0] != pair[1]
    end
  end
end
