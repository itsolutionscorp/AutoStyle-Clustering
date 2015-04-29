class Hamming

  def self.compute(source, compare)
    distance = 0
    source.chars.zip(compare.chars).each do |lhs, rhs|
      break if rhs.nil?
      distance += 1 if lhs != rhs
    end
    distance
  end
end
