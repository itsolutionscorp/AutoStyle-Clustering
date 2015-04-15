class Squares
  def initialize(number)
    @number = number.to_i
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    i = 1
    sum = 0
    @number.times do
      sum += i
      i += 1
    end

    sum * sum
  end

  def sum_of_squares
    i = 1
    sum = 0
    @number.times do
      sum += i * i
      i += 1
    end

    sum
  end
end
