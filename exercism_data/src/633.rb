def compute(a, b)
    counter = 0

    a.length.times do |i|
      if a[i] != b[i]
        counter += 1
      end
    end

    return counter
  end