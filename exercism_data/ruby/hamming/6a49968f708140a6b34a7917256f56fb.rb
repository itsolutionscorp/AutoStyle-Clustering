class Hamming
	def self.compute( a, b)
		count = 0
		index = 0

		if b.length < a.length
			a, b = b, a
		end
		strandA = a.split('')
		strandB = b.split('')

		strandA.each do |nucleotide|
			if nucleotide != 	strandB[index]
				count += 1
			end
			index += 1
		end
		return count 
	end
end
