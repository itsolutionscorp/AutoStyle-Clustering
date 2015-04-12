class Hamming 
	def compute(a, b)
		strand1 = a.split('')
		strand2 = b.split('')
		shortest_strand = strand1.length > strand2.length ? strand2 : strand1
		hamming_distance = 0

		count = 0
		while count < shortest_strand.length
			hamming_distance += 1 if strand1[count] != strand2[count]
			count += 1
		end

		puts "The hamming distance of these two DNA strands is: #{hamming_distance}"
		return hamming_distance
	end
end
