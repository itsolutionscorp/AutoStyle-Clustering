def compute(a, b)
    short, long = [a, b].sort_by(&:length).map(&:chars)
    short.zip(long).reject { |x, y| x == y }.count
  end