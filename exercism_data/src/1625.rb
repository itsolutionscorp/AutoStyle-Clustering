def compute(first, second)
    min = [first.length, second.length].min
    first[0, min].chars.zip(second[0, min].chars)
                  .reject {|points| points.first == points.last}
                  .length
  end