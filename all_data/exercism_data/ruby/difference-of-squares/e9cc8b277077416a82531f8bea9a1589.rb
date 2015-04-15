class Squares

  def initialize(number)
    @number = number
  end

  def square_of_sums
    n = 1
    i = 2
    while i <= @number do
      n += i
      i += 1
    end
    return n**2
  end

  def sum_of_squares
    n = 1
    i = 2
    while i <= @number do
      n += i**2
      i += 1
    end
    return n
  end

  def difference
    d = square_of_sums - sum_of_squares
  end


end
