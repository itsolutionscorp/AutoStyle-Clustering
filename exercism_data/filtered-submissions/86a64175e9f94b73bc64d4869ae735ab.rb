def compute(a, b)

    if a === b
      return 0
    end

    length = a.length < b.length ? a.length : b.length


    difference = 0


    for i in 0..(length-1) do
      if a[i] != b[i]
        difference = difference + 1
      end
    end
    return difference
  end