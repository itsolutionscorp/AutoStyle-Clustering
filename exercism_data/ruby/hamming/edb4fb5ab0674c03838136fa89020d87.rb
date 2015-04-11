class Hamming

	def self.compute(x, y)

		x = x.split('')
		y = y.split('')

		count = 0

		i = 0

		while i < x.length
			if x[i] != y[i] 
				count = count + 1
			end
			i = i + 1
		end

		return count 

	end

end
