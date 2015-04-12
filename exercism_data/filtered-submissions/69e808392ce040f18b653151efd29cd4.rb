def compute(strand1,strand2)
		dna_array1 = strand1.split('')
		dna_array2 = strand2.split('')
			if dna_array2.length > dna_array1.length
				min = dna_array1.length
			else
				min = dna_array2.length
			end
			assert_equal = 0
			dna_array1.each_with_index do |genome, i|
				if genome == dna_array2[i]
					assert_equal = assert_equal
				elsif dna_array2[i].nil?
					assert_equal = assert_equal
				else
					assert_equal += 1
				end
			end
		return assert_equal
	end