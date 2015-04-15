class Grains

	def square arg
		2**(arg - 1)
	end

	def total
		array = []
		for num in 1..64 
			array << Grains.new.square(num)
		end
		array.inject(:+)
	end

end
