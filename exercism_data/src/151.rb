def compute(a,b)
    [a,b].map(&:length).min.times { |i| a[i] != b[i] }
  end