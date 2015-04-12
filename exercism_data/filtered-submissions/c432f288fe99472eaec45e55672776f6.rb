module Hamming

  def compute(one, other)
    count = 0
    length = [one.length, other.length].min
    (0...length).each do |i|
      count += 1 if one[i] != other[i]
    end
    count
  end

end
