class Fixnum
  def power_of_two
    self**2
  end

  def sequence
    times.map {|x| x + 1 }.each
  end
end

class Squares
  attr_accessor :number 

  def initialize(number)
    @number = number
  end

  def square_of_sums
    @number.sequence.inject(0, :+).power_of_two 
  end

  def sum_of_squares
    @number.sequence.map(&:power_of_two).inject(0, :+)
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
