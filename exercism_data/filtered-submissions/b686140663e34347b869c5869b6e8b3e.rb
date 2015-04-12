def compute(strand, otherstrand)
    [strand, otherstrand].map(&:size).min.times.count { |n| strand[n] != otherstrand[n] }
  end