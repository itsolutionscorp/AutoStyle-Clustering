class Squares
  def initialize(num)
  	@num = num
  end

  def square_of_sums
  	res = 0
    a=*(1..@num)
    a.each do |i|
 	  res += i
 	end
 	res = res**2
  end

  def sum_of_squares

  	res = 0
  	a=*(1..@num)
  	a.each do |i|
      
      res += i**2
  	end
  	return res
  end

  def difference

  	Squares.new(@num).square_of_sums - Squares.new(@num).sum_of_squares

  end

end
