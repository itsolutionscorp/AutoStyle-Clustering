def compute(a,b)
    distance = 0
    for i in 0..([a.length, b.length].min - 1)
      if a[i] != b[i]
        distance += 1
      end
    end
    return distance
  end