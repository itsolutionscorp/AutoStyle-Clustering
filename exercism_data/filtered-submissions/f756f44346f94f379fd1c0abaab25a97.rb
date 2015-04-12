def compute(left, right)
    shortest = [left.length, right.length].min
    (0...shortest).count do |i|
      left[i] != right[i]
    end
  end