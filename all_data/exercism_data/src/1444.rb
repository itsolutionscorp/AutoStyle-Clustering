def compute(a, b)
    long, short = [a, b].sort_by(&:length).map { |s| s.split('') }
    long.zip(short).map { |x, y| x == y ? 0 : 1 }.reduce(:+)
  end