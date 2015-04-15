class Squares

  attr_accessor :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    result = 0
    for i in 1..@number
      i += i
    end
    return result**2
  end

  def sum_of_squares
    result = 0
    for i in 1..@number
      i += i**2
    end
    return result
  end

  def difference
    return square_of_sums-sum_of_squares
  end
end
