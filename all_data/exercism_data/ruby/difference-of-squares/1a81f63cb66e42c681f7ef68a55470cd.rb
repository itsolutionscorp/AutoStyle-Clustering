class Squares
  def initialize(num)
  	@num = num
  end

  def square_of_sums
  	res = 0
    @num.times do |i|
 	  res += i
 	end
  res = res**2
  end

  def sum_of_squares

  	res = 0
    @num.times do |i|
      res += i**2
  	end
  	return res
  end

  def difference

  	self.square_of_sums - self.sum_of_squares

  end

end
