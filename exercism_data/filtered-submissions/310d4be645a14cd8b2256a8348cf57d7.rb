def compute(a,b)

    if (a.length > b.length)
      a,b = b,a
    end

    distance = 0
    0.upto(a.length-1) do |i|
      distance += 1 if a[i] != b[i]
    end

    return distance
  end