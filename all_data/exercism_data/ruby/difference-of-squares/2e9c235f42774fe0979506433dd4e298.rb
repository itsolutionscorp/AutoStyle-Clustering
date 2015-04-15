class Squares

  attr_accessor :size

  def initialize arg
  	self.size = arg
  end

  def square_of_sums 
  	sum_ary = []
    number_chain = *(1..self.size)
    number_chain.each do |number|
    	sum_ary << number 
    end
    @square_of_sums = (sum_ary.inject(:+)) ** 2
  end

  def sum_of_squares 
  	sum_ary = []
  	number_chain = *(1..self.size)
  	number_chain.each do |number|
  		sum_ary << (number ** 2)
  	end
  	@sum_of_squares = sum_ary.inject(:+)
  end

  def difference 
  	(self.square_of_sums) - (self.sum_of_squares)
  end
