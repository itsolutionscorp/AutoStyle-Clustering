class Grains
	def square num
		2**(num - 1)
	end
	
	def total
		(1..64).inject {|sum, n| sum + square(n) }
	end
end
