def compute(a, b)
    errors = 0
    [a.length, b.length].min.times do |n|
      errors += 1 unless a[n] == b[n]
    end
    errors
  end