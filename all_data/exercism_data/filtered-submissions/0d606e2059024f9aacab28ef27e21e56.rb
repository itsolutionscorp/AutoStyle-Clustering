def compute(first, second)
    distance = 0

    [first.length, second.length].min.times do |index|
      distance += 1 if first[index] != second[index]
    end

    distance
  end