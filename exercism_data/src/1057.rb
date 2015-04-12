def compute(x, y)
    x.split('').zip(y.split('')).map { |x, y| x == y || x.nil? || y.nil? ? 0 : 1 }.inject(&:+)
  end