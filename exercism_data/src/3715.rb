def compute(a, b)
    [a, b].min_by(&:length).length.times.map { |idx| a[idx] == b[idx] }.select(&:!).length
  end