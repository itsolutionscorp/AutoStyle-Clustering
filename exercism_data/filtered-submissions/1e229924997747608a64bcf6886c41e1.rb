class Hamming

	def compute(a, b)
		@count = 0
		for x in 0..a.length-1
			@count += 1 if a[x] != b[x]
		end
		return @count
	end

end
