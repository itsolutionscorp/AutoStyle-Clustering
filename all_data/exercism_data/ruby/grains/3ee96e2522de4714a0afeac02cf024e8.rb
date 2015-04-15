class Grains
	def initialize()
	end
	def square(number)
		2**(number-1)
	end
	def total
		num = 1
		acc = 0
		until num > 64
			acc += (square(num))
			num+=1
		end
		acc
	end
	
end
