class Squares
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    @total = []
    (0..@number).each do |number|
      @total << number**2
    end
    @total.inject(:+)
  end

  def square_of_sums
    (0..@number).inject(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
