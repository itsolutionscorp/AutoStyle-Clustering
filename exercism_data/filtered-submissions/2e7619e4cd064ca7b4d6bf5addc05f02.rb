class Hamming
  def compute(first, second)
    distance, min_size = 0, [first.size, second.size].min
    (0...min_size).each { |i| distance += 1 if first[i]!=second[i] }
    distance
  end
end
