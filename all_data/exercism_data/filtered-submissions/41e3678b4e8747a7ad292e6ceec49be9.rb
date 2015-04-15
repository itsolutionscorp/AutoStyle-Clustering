def compute(first, second)

    first = first.to_s
    second = second.to_s

    len = [first.length, second.length].min
    dist = 0
    len.times {|i| dist += 1 unless first[i] == second[i]}
    dist
  end