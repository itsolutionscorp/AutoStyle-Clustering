class Squares

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    accumulate {|sum,i| sum + i}**2
  end

  def sum_of_squares
    accumulate {|sum,i| sum + i**2}
  end

  def accumulate(&step)
    (1..number).reduce do |sum, i|
      step.call(sum,i)
    end 
  end

end
