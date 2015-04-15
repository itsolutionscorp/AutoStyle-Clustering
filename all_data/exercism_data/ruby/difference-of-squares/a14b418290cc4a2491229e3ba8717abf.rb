class Squares

  attr_accessor :number

  def initialize(num)
    @number = num
  end

  # private

  def square_of_sums
    sumatory**2
  end
  
  def sumatory
    1.upto(@number).inject(:+)
  end

  def sum_of_squares
    1.upto(@number).collect {|item| item**2}.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
