class Grains
	def square(n)
		2 ** (n-1)
	end

	def total
		1.upto(64).inject(0){|result, element| result + square(element) }
	end
end
