def compute(a, b)
    differences = 0
    [a.length ,b.length].min.times do |index|
      differences += 1 if a[index] != b[index]
    end
    return differences
  end