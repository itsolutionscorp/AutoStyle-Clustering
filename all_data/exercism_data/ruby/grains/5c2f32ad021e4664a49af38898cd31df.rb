class Grains

	def square (numb)
		2**(numb-1)
	end

	def total
		(0...64).inject { |sum, n| sum + 2**(n) } + 1	
	end

end
