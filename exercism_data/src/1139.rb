class Hamming
  def compute(first, second)
    distance = 0
    length = [ first.length, second.length ].min
    length -= 1
    (0..length).each do |i|
      distance += 1 if first[i] != second[i]
    end
    return distance
  end
end
