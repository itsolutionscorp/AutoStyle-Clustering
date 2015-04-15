class Squares
  #Initializing and ... I have no idea what I am doing here :/
  def initialize(n)
    @n = n
  end
  #going through all the numbers from 1 to n
  #squaring then and injecting a "+" between each step
  def self.sum_of_squares(n)
    (1..n).map{|i| i**2}.inject(:+)
  end
  #injecting a "+" on the 1 to n sequence to get the sum
  #then squaring it to get the result
  def self.square_of_sums(n)
    sum = (1..n).map{|i| i}.inject(:+)
    sum**2
  end
  #This can be done in ternary 
  def self.difference
    square_of_sums - sum_of_squares
  end
end
