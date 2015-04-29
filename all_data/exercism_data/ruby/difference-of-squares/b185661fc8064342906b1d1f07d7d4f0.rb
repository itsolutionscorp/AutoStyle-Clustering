class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    @number.times.collect {|i| i+1}.reduce(:+)**2
  end

  def sum_of_squares
    @number.times.collect { |i| (i + 1)**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
