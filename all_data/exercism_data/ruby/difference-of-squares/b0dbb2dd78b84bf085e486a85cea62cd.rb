require 'benchmark'
class Squares
  attr_accessor :num

  def initialize(num)
    self.num=num
  end

  def sum_of_squares #Calculated using square pyramidal formula
    (num*(num+1)*(2*num+1))/6
  end

  def square_of_sums
    sum**2
  end

  def difference
    (sum_of_squares-square_of_sums).abs
  end

  private
  def sum #Calculated using triangular number formula
    (num*(num+1))/2
  end
end
