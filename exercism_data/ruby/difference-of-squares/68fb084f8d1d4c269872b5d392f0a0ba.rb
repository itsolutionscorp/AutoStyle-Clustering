class Fixnum
  def power_of_two
    self**2
  end
end

class Squares
  attr_accessor :number 

  def initialize(number)
    @number = number
  end

  def square_of_sums
    (1..@number).inject(0, :+).power_of_two
  end

  def sum_of_squares
    (1..@number).map(&:power_of_two).inject(0, :+)
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
