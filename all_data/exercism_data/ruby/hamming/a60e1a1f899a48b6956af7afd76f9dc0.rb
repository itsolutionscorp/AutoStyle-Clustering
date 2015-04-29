class Hamming
	def self.compute (a , b)
		hamming_distance = 0
		i=0

		if a.length > b.length
			c = a
			a = b
			b = c
		end

		while i < a.length
			if a[i] != b[i]
				hamming_distance += 1
			end
			i += 1
		end
	return hamming_distance

	end
end
