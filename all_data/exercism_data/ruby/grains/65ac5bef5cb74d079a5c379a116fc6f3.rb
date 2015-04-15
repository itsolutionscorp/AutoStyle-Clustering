class Grains
	
	def square(number)
		2**(number-1)
	end

	def total
		(0..63).to_a.inject(0) {|s,i| s+= 2**i}
	end
end
