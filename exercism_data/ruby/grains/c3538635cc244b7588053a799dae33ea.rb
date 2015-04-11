class Grains

  TOTAL_SQUARES = 64

  def square(n)
    2 ** (n - 1)
  end

  def total
    1.upto(TOTAL_SQUARES).inject(0) {|total, n| total + square(n)}
  end

end
