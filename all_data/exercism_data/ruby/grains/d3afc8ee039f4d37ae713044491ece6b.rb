class Grains
	def square(num)
		2 ** (num-1)
	end
	def total()
		total = 0
		i=1
		until i > 64
			total += square(i)
			i += 1
		end
		return total
	end
end
