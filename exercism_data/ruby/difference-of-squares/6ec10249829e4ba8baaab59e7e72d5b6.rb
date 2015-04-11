class Squares
  def initialize number
    @number = number
  end

  def square_of_sums
    array_of_numbers.inject(:+) ** 2
  end

  def sum_of_squares
    array_of_numbers.map { |number| number ** 2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

private

  def array_of_numbers
    @array_of_numbers ||= (1..@number)
  end
end
