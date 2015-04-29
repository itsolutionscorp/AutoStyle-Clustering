class Grains
	def square(num)
		i=1
		suma = 0
		cal = 0
		while i <= num
			cal=2**(i-1)
			return cal
			suma+=cal
			i=i+1
		end
	end
	def total()
		return suma
	end
end
