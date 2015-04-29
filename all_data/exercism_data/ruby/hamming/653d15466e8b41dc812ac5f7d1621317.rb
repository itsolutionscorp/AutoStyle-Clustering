class Hamming
	def self.compute(x, y)
		i = 0
		(0..(x.length-1)).each do |z|
			i+=1 if x[z] != y[z]
		end
		i
	end
end
