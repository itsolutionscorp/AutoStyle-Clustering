class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    first_n_nums.reduce(:+)**2
  end

  def sum_of_squares
    first_n_nums.map { |num| num**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def first_n_nums
    1..number
  end
end
