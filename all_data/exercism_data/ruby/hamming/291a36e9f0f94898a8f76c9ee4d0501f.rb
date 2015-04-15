class Hamming
  def self.compute(first, second)
    if first==second
      0
    else
      distance = 0
      min_size = [first.size, second.size].min
      for i in (0...min_size)
        distance += 1 if first[i]!=second[i]
      end
      return distance
    end
  end
end
