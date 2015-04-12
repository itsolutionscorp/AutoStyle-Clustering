def compute(lhs, rhs)
    return nil if lhs.length != rhs.length
    diff = 0
    lhs.length.times do |i|
      diff += lhs[i] != rhs[i] ? 1 : 0
    end
    return diff
  end