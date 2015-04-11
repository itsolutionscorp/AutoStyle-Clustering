class Squares
  def initialize n
  	@arr = stair n
  end

  def square_of_sums #Just like Gauss did.
  	f = @arr.size / 2.0
  	s = ( @arr.first + @arr.last ) * f.to_i
  	f == f.to_i ? s**2 : (s + @arr[f.to_i])**2
  end

  def sum_of_squares
  	s = 0
  	( @arr.map { |e| s += (e**2)} ).last
  end

  def difference
  	self.square_of_sums - self.sum_of_squares
  end

  private
  def stair i
  	if i == 1 then	return [i]	end
	stair(i-1) << i
  end
end
