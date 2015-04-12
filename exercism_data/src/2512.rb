class Hamming
	def compute(strand1, strand2)
		difference_counter = 0

		strand1.length.times do |i|
			if strand1[i] != strand2[i]
				difference_counter += 1
			end
		end

		difference_counter
	end
end
