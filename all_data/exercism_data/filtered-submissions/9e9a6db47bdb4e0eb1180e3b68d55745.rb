def compute(left, right)
    left.chars.zip(right.chars).reduce(0) do |sum, value|
      value[0] != value[1] ? 1 + sum : sum
    end
  end