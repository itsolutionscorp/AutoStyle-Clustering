def compute(a, b)
		strand1 = a.split('')
		strand2 = b.split('')
		shortest_strand = strand1.length > strand2.length ? strand2 : strand1
		hamming_distance = 0

		0.upto(shortest_strand.length) do |x|
			hamming_distance += 1 if strand1[x] != strand2[x]
			count += 1
		end

		puts "The hamming distance of these two DNA strands is:
		return hamming_distance
	end