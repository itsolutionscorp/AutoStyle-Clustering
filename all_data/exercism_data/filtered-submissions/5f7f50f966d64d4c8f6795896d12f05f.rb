def compute(one, two)
    distance = 0
    one.chars.each_with_index {|item, i| distance += 1 if (item != two[i]) }
    return distance
  end