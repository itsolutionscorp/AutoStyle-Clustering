class Hamming

	def self.compute(x,y)
		if x.length == y.length
			i = 0
			sum = 0
			for i in (0..x.length)
				if x[i] == y[i]
					sum += 0
					i += 1
				else
					sum += 1
					i += 1
				end
			end
			return sum
		else
			puts "strings are not equal length"
			exit
		end
	end

end
