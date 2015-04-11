class Grains
	attr_accessor :square

	def square arg
		self.square = 2**(arg - 1)
	end

	def total
		array = []
		range = *(1..64)
		for num in range 
			array << Grains.new.square(num)
		end
		array.inject(:+)
	end

end
