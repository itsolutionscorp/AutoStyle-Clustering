def compute(first_strand, second_strand)
		hamming_number, i = 0, 0
		string_length = [first_strand.length, second_strand.length].max
		while i < string_length do
			if first_strand[i] != second_strand[i] then hamming_number += 1 end
			i += 1
		end
		hamming_number
	end