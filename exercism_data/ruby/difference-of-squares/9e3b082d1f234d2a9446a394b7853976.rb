class Squares
  def initialize(number)
    @numbers = (1..number).to_a
  end

  def square_of_sums
    sums ** 2
  end

  def sum_of_squares
    sum(squares)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
  def sums
    sum(@numbers)
  end

  def squares
    @numbers.map { |n| n ** 2 }
  end

  def sum(numbers)
    numbers.inject(:+)
  end
end
