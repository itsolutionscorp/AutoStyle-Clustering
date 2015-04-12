def compute(a, b)
    len = a.length
    count = 0
    for i in 0..len-1
      if a[i] != b[i]
        count += 1
      end
    end
    count
  end