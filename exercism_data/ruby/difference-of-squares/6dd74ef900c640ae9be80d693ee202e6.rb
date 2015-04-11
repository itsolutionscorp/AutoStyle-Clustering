class Squares
  def initialize digit
		@digit = digit
  end
	
	def square_of_sums
		(1..@digit).inject(:+)**2		
	end

	def sum_of_squares
		(1..@digit).inject(0) { |sum, digit| sum + (digit**2)}
	end

	def difference
		square_of_sums - sum_of_squares	
	end
end
