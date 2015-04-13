def compute(first, second)
    dist = 0
    for i in 0..first.length
      dist += first[i] == second[i] ? 0 : 1
    end
    return dist
  end