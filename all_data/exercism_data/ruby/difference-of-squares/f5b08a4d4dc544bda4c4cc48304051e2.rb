class Squares
  def initialize number
    @number = number
  end

  def square_of_sums
    (1..@number).inject(:+) ** 2
  end

  def sum_of_squares
    (1..@number).inject(0) do |acc, num|
      acc += num**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
