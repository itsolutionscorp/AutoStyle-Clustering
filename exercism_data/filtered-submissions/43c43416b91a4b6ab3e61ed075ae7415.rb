def compute(first, second)
    distance = 0
    length   = [first.length, second.length].min

    length.times do |n|
      distance += 1 if first[n] != second[n]
    end

    distance
  end