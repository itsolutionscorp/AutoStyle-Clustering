class Integer 
  def squared 
    self ** 2 
  end 
end 

class Array    
  def sum 
    self.inject &:+ 
  end 

  def squares 
    self.map &:squared 
  end 
end 


class Squares 
  attr_reader :n 
  def initialize n 
    @n = n 
  end 

  def set
    (1..self.n).to_a    
  end 

  def square_of_sums
    self.set.sum.squared
  end 

  def sum_of_squares 
    self.set.squares.sum 
  end 

  def difference 
    self.square_of_sums - self.sum_of_squares
  end 
end
