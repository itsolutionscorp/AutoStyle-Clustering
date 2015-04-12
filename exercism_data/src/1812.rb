def compute(one, two)
    limit = [one.size, two.size].min
    (0...limit).count { |i| one[i] != two[i] }
  end