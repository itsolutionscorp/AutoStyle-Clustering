class Hamming
	def self.compute(a, b)
		n = [a.length, b.length].min
		diff = 0
		for i in 0..(n - 1)
			if (a[i] != b[i])
				diff += 1
			end
		end
		return diff
	end
end
