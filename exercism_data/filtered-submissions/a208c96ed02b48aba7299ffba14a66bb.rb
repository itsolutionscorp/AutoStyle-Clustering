module Hamming
  def compute first, other
    count = 0
    minimum = [first.size, other.size].min

    (0...minimum).each do |index|
      count += 1 if first[index] != other[index]
    end

    count
  end
end
