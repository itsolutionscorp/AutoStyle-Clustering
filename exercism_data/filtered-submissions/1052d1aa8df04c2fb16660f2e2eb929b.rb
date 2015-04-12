def compute(strand1, strand2)
		shorter_length = strand1.length <= strand2.length ? strand1.length : strand2.length
		mutations = 0
		shorter_length.times do |position|
			mutations += 1 if strand1[position] != strand2[position]
		end
		mutations
	end