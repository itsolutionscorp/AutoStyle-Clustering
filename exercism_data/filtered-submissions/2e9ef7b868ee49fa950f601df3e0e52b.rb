def compute(left, right)
      left.
        chars.
        zip(right.chars).
        reject { |l, r| l == r }.
        length
    end