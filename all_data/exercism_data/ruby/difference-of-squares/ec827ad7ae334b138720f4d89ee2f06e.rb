class Squares
  attr_reader :sum_of_squares, :square_of_sums

  def initialize(number)
    @number = number
    self
  end

  def square_of_sums
    @current_sum = 0
    @number_array = [*1..@number]
    @number_array.each do |n|
      @current_sum += n
    end
    @current_sum ** 2
  end

  def sum_of_squares
    @current_square = 0
    @number_array = [*1..@number]
    @number_array.each do |n|
      @current_square += n ** 2
    end
    @current_square
  end

  def difference
    @difference = square_of_sums - sum_of_squares
  end
end
