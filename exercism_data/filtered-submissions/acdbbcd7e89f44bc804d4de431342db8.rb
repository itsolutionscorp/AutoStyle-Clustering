def compute(a, b)
    raise ArgumentError if a.length != b.length

    count = 0
    (0 ... a.length).each do |i|
      count += 1 if a[i] != b[i]
    end

    count
  end