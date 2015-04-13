def compute(a, b)
    count = 0
    max = a.length < b.length ? a.length : b.length
    for i in 0...max
      if a[i] != b[i]
        count += 1
      end
    end
    return count
  end