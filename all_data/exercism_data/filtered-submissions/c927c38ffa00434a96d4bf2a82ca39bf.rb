def compute(lhs, rhs)
    return nil if lhs.length != rhs.length
    diff = 0
    for i in 0..lhs.length-1 do
      diff += lhs[i] != rhs[i] ? 1 : 0
    end
    return diff
  end