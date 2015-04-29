def compute(strand1, strand2)
		diff = 0
		strand1 = strand1.split("")
		strand2 = strand2.split("")
		strand1.each_with_index do |char, index|
			if strand2[index] != nil && char != strand2[index]
				diff = diff + 1
			end
		end
		diff
	end