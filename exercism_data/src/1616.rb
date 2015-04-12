module Hamming
  extend self

  def compute first, other
    count = 0

    equal_string = [first.size, other.size].min

    first[0...equal_string].chars.each_with_index do |char, index|
      count += 1 if char != other[index]
    end

    count
  end
end
