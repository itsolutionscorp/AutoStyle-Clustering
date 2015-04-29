class Grains

  def initialize
    @squares = []
  end

  def square(n)
    sqrs = (1..n).to_a

    until sqrs.empty? do
      n = sqrs.slice!(0)
      @squares << add_square(n)
    end

    @squares.last
  end

  def add_square(n)
    return 1 if n == 1
    @squares.select{ |t| !t.nil? }.last * 2
  end

  def total
    total, sq_grain = 0, square(64)

    until sq_grain == 1 do
      total += sq_grain
      sq_grain /= 2
    end

    total + 1
  end

end
