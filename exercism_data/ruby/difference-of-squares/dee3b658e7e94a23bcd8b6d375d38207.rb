class Squares

  def initialize (number)
    @number = number
  end

  def square_of_sums
    number = @number
    total = 0
    while number > 0
      total += number
      number = number - 1
    end
    total ** 2
  end

  def sum_of_squares
    number = @number
    total = 0
    while number > 0
      total += number**2
      number = number - 1
    end
    total
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
