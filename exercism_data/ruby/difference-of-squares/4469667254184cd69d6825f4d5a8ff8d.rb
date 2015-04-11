class Squares
  def initialize(number)
    @num = number
  end

  def square_of_sums
    (1..@num).to_a.reduce(:+)**2
  end

  def sum_of_squares
    (1..@num).to_a.map do |num|
      num**2
    end.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
