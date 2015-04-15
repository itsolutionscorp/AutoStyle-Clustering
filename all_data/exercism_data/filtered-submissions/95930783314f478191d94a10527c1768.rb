def compute(lhs, rhs)
    return nil if lhs.length != rhs.length
    lhs.split('').zip(rhs.split('')).count { |pair| pair[0] != pair[1] }
  end