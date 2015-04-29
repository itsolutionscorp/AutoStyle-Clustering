def compute(first, second)
    [first.length, second.length].min.times.count {|i| first[i] != second[i]}
  end