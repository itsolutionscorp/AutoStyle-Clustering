class Squares

  def initialize(number)

    @number = number

  end

  def square_of_sums

    count = 1
    count2 = 1

    while count < @number
      count = count + 1
      count2 = count + count2
    end

    count2**2

  end

  def sum_of_squares

    count = 1
    count2 = 1

    while count < @number
      count = count + 1
      count2 = count**2 + count2
    end

    count2

  end

  def difference

    square_of_sums - sum_of_squares

  end

end
