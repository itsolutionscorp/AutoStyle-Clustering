def compute(first, second)

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