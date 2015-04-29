def compute(left, right)
    diff = 0
    0.upto([left.length, right.length].min - 1) do |i|
      diff = diff + 1 if left[i] != right[i]
    end
    diff
  end