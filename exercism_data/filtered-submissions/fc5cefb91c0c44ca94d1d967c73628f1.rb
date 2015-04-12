def compute(left, right)
    diff = 0
    0.upto([left.length, right.length].min - 1) do |i|
      if left[i] != right[i]
        diff = diff + 1
      end
    end
    diff
  end