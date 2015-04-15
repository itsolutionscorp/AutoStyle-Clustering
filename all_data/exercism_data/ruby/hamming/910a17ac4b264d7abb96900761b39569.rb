class Hamming

	def self.compute(dna1, dna2)

		array1 = dna1.split('')
		array2 = dna2.split('')
		count = 0

		array1.each.with_index do |piece, i|
			if piece != array2[i]
				count += 1
			else
				count += 0
			end
		end


		diff = array1.length - array2.length

		if array1.length > array2.length
			count - diff.abs 
		else
			count
		end
	end
end
