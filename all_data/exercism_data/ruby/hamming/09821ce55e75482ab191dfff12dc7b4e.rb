module Hamming
  def self.compute(first, second)
    distance = 0
    first.split('').each_with_index do |char, i|
      distance = distance + 1 if char != second[i]
    end
    distance
  end
end
