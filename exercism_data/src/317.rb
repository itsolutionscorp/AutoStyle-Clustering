def compute a, b
    [a,b].map(&:size).min.times.count do |x|
      a[x] != b[x]
    end
  end