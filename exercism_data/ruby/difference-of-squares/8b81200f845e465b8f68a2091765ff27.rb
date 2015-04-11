class Integer 
  def squared 
    self ** 2 
  end 
end 

module Enumerable     
  def sum 
    inject &:+ 
  end 

  def squares 
    map &:squared 
  end 
end 


class Squares 
  attr_reader :set  
  def initialize k 
    @set = (1..k)
  end 

  def square_of_sums
    set.sum.squared
  end 

  def sum_of_squares 
    set.squares.sum 
  end 

  def difference 
    square_of_sums - sum_of_squares
  end 
end
