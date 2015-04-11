class Hamming
	def self.compute(a, b)
		diff = 0
		diff_length = [a.length, b.length].min
		for i in (0 .. diff_length - 1)
			diff += 1 if a[i] != b[i]
		end
		return diff
	end
end
