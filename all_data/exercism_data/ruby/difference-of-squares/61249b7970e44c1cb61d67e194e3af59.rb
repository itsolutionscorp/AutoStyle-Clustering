class Squares

  attr_reader :number

  def initialize(number)
    @number = number.to_i
  end

  def block_iterator
    number.times.inject(0) {|result, num| result += (yield num+1)}
  end

  def square_of_sums
    (block_iterator{ |n| n })**2
  end
  
  def sum_of_squares
    block_iterator{|n| n**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
