def compute(sequence1,sequence2)
		array1 = sequence1.split('')
		array2 = sequence2.split('')
		@@Hamming = 0
		array1.each_with_index do |letter,i|
			if i < array1.length && i < array2.length
				if array1[i] != array2[i]
					@@hamming += 1
				end
			end
		end
		return @@hamming
	end