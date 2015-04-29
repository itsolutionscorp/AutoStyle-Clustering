class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = @number
    @number.times do |number|
      sum += number
    end
    sum*sum
  end

  def sum_of_squares
    sum = @number*@number
    @number.times do |number|
      sum += number*number
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
