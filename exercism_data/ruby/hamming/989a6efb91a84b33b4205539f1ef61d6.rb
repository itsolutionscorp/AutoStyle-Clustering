class Hamming
  def self.compute(first, second)
    distance = first.chars.zip(second.chars).each.count do |a, b|
      a != b
    end
    distance
  end
end