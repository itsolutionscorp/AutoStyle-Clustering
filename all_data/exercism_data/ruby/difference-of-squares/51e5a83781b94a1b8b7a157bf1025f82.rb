class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    newnum = @num
    countr = @num
    while countr > 0
      newnum += (countr - 1)
      countr -= 1
    end
    newnum**2
  end

  def sum_of_squares
    sumsquars = 0
    while @num > 0
      sumsquars += @num**2
      @num -= 1
    end
    sumsquars
  end

  def difference
    newnum = @num
    countr = @num
    while countr > 0
      newnum += (countr - 1)
      countr -= 1
    end
    squaresums = newnum**2

    sumsquars = 0
    while @num > 0
      sumsquars += @num**2
      @num -= 1
    end
    squaresums - sumsquars
  end
end
