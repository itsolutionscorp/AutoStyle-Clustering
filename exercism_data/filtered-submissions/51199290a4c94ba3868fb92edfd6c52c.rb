def compute(left, right)
    min_size = [left.size, right.size].min
    (0...min_size).count { |offset| left[offset] != right[offset] }
  end