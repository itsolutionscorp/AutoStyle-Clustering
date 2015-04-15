class Squares

  def initialize(number)
    @number = number
    @numbers = []
  end

  def get_numbers
    while @number > 0
      @numbers << @number
      @number-=1
    end
  end

  def sum_of_squares
    get_numbers
    @numbers.inject(0){|sum, x| sum + x**2}
  end

  def square_of_sums
    get_numbers
    result =  @numbers.inject(0){|sum, x|  sum + x }
    result**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
