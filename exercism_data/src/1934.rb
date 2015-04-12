def compute(d1, d2)
    d1.chars.zip(d2.chars)
            .reject { |c| c.any?(&:nil?) }
            .map { |a,b| a == b ? 0 : 1 }
            .inject(:+)
  end