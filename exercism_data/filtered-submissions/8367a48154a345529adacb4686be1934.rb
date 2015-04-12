def compute(stringOne, stringTwo)
    [stringOne, stringTwo].min.size.times.count { |i| stringOne[i] != stringTwo[i] }
  end