def compute (strand1, strand2)

		index = 0
		dissim = 0
		length = strand1.length



		while index != length do
			dissim += 1 unless strand1[index] == strand2[index]
			index += 1
		end
		dissim
	end