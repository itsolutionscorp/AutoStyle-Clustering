class Squares
  def initialize n
  	@arr =  (1..n).to_a
  end

  def square_of_sums #Just like Gauss did.
  	f = @arr.size / 2.0
  	s = ( @arr.first + @arr.last ) * f.to_i
  	f == f.to_i ? s**2 : (s + @arr[f.to_i])**2
  end

  def sum_of_squares
  	s = 0
  	( @arr.map { |e| s += (e**2)} ).last
  	# Seen at nitpicks :
  	# @arr.map(&:abs2).inject(:+)
  end

  def difference
  	self.square_of_sums - self.sum_of_squares
  end

end
