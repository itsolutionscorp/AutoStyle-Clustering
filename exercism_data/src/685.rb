def compute(a,b)
    difference = 0

    length = a.length < b.length ? a.length : b.length

    for i in 0..(length - 1)
      difference += 1 if a[i] != b[i]
    end

    difference
  end