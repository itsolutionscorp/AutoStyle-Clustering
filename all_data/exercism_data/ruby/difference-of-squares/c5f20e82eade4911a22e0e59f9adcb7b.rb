require 'benchmark'
class Squares
  private
  attr_accessor :num

  def sum #Calculated using triangular number formula
    (num*(num+1))/2
  end

  def initialize(num)
    self.num=num
  end

  public
  def sum_of_squares #Calculated using square pyramidal formula
    (num*(num+1)*(2*num+1))/6
  end

  def square_of_sums
    sum**2
  end

  def difference
    square_of_sums-sum_of_squares #since square_of_sums >= sum_of_squares
  end
end
