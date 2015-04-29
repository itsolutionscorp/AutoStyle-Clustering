def compute(x, y)
    compare = (x.length == y.length || x.length < y.length) ? x.length : y.length
    distance = 0
    compare.times do |check|
      if x[check] != y[check]
        distance += 1
      end
    end
    distance
  end