class Grains

	def square arg
		2**(arg - 1)
	end

	def total
		(1..64).map do |x|
			square(x)
		end.inject(:+)
	end

end
