def compute(a, b)
    return_value = 0
    (0..(a.length - 1)).each do |i|
      if a[i] != b[i]
        return_value += 1
      end
    end
    return_value
  end