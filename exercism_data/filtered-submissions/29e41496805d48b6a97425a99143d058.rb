def compute(a, b)
    shorter = [a.length, b.length].min - 1
    a_trimmed = a[0..shorter]
    b_trimmed = b[0..shorter]
    distance = 0
    for i in 0..shorter
      if a[i] != b[i]
      	distance += 1
      else
      end
    end
    return distance
  end