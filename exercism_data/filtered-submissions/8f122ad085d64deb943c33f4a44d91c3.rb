def compute(object_one, object_two)
    matches = (0...object_one.length).select { |index| object_one[index] != object_two[index]}
    matches.count
  end