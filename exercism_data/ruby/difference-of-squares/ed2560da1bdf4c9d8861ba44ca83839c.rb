class Squares

  def initialize(num)
    @number = num
  end

  def square_of_sums
    number = 0
    x = 1
    while x <= @number
      number += x
      x += 1
    end
    number ** 2
  end

  def sum_of_squares
    number = 0
    x = 1
    while x <= @number
      number += x**2
      x += 1
    end
    number
  end

  def difference
    number = sum_of_squares - square_of_sums
    number.abs
  end

end
