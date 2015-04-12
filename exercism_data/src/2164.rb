def compute(first, second)
    # assuming length is the same for both inputs
    if first.length > second.length
      length = second.length
    else
      length = first.length
    end
    distance = 0
    length.times do |index|
      if first[index] != second[index]
        distance += 1
      end
    end
    return distance
  end