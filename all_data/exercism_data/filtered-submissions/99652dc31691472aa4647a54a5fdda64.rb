def compute(a, b)

    differences = (0...[a.length, b.length].min).collect { |i| "Difference!" unless a[i] == b[i] }
    differences.count("Difference!")
  end