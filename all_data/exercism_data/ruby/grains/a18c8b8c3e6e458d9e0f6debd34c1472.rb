class Grains

	def square arg
		2**(arg - 1)
	end

	def total
		(1..64).map {|x| square(x)}.inject(:+)
	end

end
