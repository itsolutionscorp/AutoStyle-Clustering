def compute(strand1, strand2)

		dissimilarity_count = 0

		if strand1.length > strand2.length
			smallest_string_length = strand2.length
		elsif strand1.length < strand2.length
			smallest_string_length = strand1.length
		else
			smallest_string_length = strand1.length
		end

		smallest_string_length.times do |i|
			if strand1[i] != strand2[i]
				dissimilarity_count += 1
			end
		end

		dissimilarity_count

	end