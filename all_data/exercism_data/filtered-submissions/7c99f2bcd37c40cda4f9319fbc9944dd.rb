def compute(left, right)
    left.bytes.zip(right.bytes).count { |n| n.all? && n.uniq.size != 1 }
  end