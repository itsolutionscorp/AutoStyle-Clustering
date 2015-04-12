def compute(left, right)
    score = 0
    size = [left.size, right.size].min
    (0..size-1).each { |i|
      score += 1 if left[i] != right[i]
    }
    score