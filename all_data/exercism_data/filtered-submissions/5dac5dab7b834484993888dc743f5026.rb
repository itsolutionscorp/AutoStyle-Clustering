def compute(left, right)
    (0 .. [left.size, right.size].min - 1).inject(0) do |a, i|
      left[i] == right[i] ? a : a + 1
    end
  end