class Hamming
	def self.compute(x, y)
		diff = 0
		max = [x.length, y.length].min
		for i in 0...max
			x[i] != y[i] ? diff += 1 : nil
		end
		diff
	end
end
