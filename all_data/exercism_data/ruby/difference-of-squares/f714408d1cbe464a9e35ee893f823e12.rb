require 'pry-nav'
class Squares
  attr_accessor :num, :sum1, :sum2

  def initialize(num)
    @num = num
    @sum1 = 0
    @sum2 = 0
  end

  def square_of_sums
    (1..@num).each do |n|
      @sum1 += n
      end
    @sum1 = @sum1 **2
  end

  def sum_of_squares
    (1..@num).each do |n|
      @sum2 += n**2
    end
    @sum2
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
