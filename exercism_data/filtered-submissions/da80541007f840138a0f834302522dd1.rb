def compute(first, second)
    distance = 0
    length = [first.size, second.size].min
    length.times do |i|
      distance += 1 if first[i] != second[i]
    end
    distance
  end