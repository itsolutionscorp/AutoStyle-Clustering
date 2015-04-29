class Squares
  attr_accessor :square_of_sums 
  attr_accessor :sum_of_squares 
  attr_accessor :difference 

  def initialize(limit)
    @square_of_sums = (1..limit).reduce(:+)**2
    @sum_of_squares = (1..limit).inject { |sum, n| sum + n**2 }
    @difference = @square_of_sums - @sum_of_squares  
  end
end
