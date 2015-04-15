class Squares

  def initialize(a_number)
    @number = a_number
  end

  def sum_of_squares
    #load cached result if it exists
    if @sum_of_squares
      return @sum_of_squares
    end

    #Cache result does not exist, calculate it
    result = 0
    (0..@number).each do |value|
      result += value * value
    end

    @sum_of_squares = result
    return @sum_of_squares
  end

  def square_of_sums
    #load cached result if it exists
    if @square_of_sums
      return @square_of_sums
    end

    #Cache result does not exist, calculate it

    #The sum of the numbers is given by the formula
    sum = (@number * (@number + 1)) / 2

    @square_of_sums = sum*sum
    return @square_of_sums
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
