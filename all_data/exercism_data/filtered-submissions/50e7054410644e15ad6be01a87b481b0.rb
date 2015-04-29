def compute (dna1, dna2)
	dna1a = dna1.split('')
	dna2a = dna2.split('')

	zippedarray = dna1a.zip dna2a

	count = 0

	zippedarray.each do |i|
		if i[0] != nil && i[1] != nil && i[0].to_s != i[1].to_s
			count = count + 1
		end
	end

	return count

end