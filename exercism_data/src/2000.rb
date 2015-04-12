def compute(first, second)
    diff  = 0

    index = 0
    while index < [first.size, second.size].min
      if first[index] != second[index]
        diff += 1
      end
      index += 1
    end

    return diff
  end