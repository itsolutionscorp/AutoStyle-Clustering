def compute(one, two)
    limit = [one.size, two.size].min
    (0...limit).select { |i| one[i] != two[i] }.size
  end