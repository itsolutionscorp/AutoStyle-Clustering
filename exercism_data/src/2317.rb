def compute(left, right)
    left.chars.zip(right.chars).reduce(0) do |sum, (left_char, right_char)|
      sum + (left_char == right_char ? 0 : 1)
    end
  end