class Hamming
  def compute(lhs, rhs)
    return nil if lhs.length != rhs.length
    diff = 0
    lhs.split('').each_with_index {|v,i| diff += 1 unless rhs[i] == v}
    return diff
  end
end
