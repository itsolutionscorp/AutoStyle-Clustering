class Grains
	def initialize
	end
	def square(square)
		return 1 << square-1
	end
	def total
		sum = 0
		for i in 1..64
			sum+=1<<i-1
		end
		sum
	end
end
