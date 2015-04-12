def compute(a, b)
    (0..a.length - 1).reduce(0) { |difference,idx| a[idx] == b[idx] ? difference : difference + 1 }
  end