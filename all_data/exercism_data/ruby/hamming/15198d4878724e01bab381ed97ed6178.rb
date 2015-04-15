module Hamming
  def self.compute(first, second)
    length = [first.size, second.size].min
    length.times.count do |i|
      first[i] != second[i]
    end
  end
end
