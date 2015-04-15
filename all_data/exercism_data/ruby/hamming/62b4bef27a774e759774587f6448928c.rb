class Hamming
	def self.compute(x, y)
		count = 0
		(0..(x.length - 1)).each do |i|
			count += 1 if x[i] != y[i]
		end
		count
	end
end
