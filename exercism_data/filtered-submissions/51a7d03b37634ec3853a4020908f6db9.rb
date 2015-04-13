def compute(a,b)
    distance = [a,b].map(&:size).min
    distance.times.map { |i| a[i] != b[i] }.select { |i| i }.size
  end