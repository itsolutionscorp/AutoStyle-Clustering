class Hamming
	def compute(a,b)

		test_length = a.length < b.length ? a.length : b.length

		distance = 0
		i = 0

		while i < test_length
			distance += 1 if a[i] != b[i]
			i += 1
		end
		
		distance
	end
end
