class Grains
	def square(squareNumber)
		2**(squareNumber-1)		
	end

	def total
		(1..64).inject(0) {|total, num| 2**num-1}
	end
end
