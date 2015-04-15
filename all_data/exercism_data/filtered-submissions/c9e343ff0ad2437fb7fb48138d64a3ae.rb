def compute(strand_1, strand_2)
	nucleotide_number = strand_1.length
	hamming_number = 0

		for y in (0..(nucleotide_number + 1)) do

			if strand_1[y] != strand_2[y]
				hamming_number += 1
			end

		end

	return hamming_number

	end