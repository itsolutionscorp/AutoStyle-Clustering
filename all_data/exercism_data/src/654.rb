def compute(x, y)
    compare = [x.length, y.length].min
    distance = 0
    compare.times do |check|
      if x[check] != y[check]
        distance += 1
      end
    end
    distance
  end