def compute (dna1, dna2)
	dna1a = dna1.split('')
	dna2a = dna2.split('')

	zippedarray = dna1a.zip dna2a
	count = 0

	dna1a.each do |i|
		if i[0] == i[1]
			count += 1
		else
			count == count
		end
	end
end