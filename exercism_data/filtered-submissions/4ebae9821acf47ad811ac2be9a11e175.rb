def compute(seq1, seq2)

		nucleotide_to_check = [seq1.length, seq2.length].min

		hamming_dist = 0

		(0...nucleotide_to_check).each do |index|
			if seq1[index] != seq2[index]
				hamming_dist += 1
			end
		end

		hamming_dist

	end