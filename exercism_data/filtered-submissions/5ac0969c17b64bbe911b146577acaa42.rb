def compute(a, b)
    errors = 0
    a.length.times do |i|
      errors += 1 if a[i] != b[i]
    end
    errors
  end