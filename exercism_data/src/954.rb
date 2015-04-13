def compute (from, to)
    diff = 0
    [from.length, to.length].min.times do |i|
      diff += 1 if from[i] != to[i]
    end
    diff
  end