class Hamming

	def compute(strand1, strand2)
		hamming = 0
		i = 0
			array1 = strand1.split('')
		array2 = strand2.split('')
	while i < array1.length && i < array2.length

		if array1[i] != array2[i]
			hamming += 1
		end

		i += 1

		end
	return hamming
	end


end
