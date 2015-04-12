class Hamming
	def compute(strand1,strand2)
		distance = 0
		@strand1 = strand1.chars
		@strand2 = strand2.chars
		@c = 0
		while @c < strand1.length && @c < strand2.length
			if strand1[@c] != strand2[@c]
				distance += 1
			end
			@c += 1
		end
		distance
	end
end

puts Hamming.compute("abc","adc")
