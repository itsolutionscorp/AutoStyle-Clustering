class Hamming
	def self.compute(strand1, strand2)
		count = 0
		difference = 0

		while count < Hamming.shorter(strand1, strand2)
			if strand1[count] != strand2[count]
				difference += 1
			end

			count += 1
		end

		return difference
	end

	def self.shorter(str1, str2)
		if str1.length > str2.length
			return str2.length
		else
			return str1.length
		end
	end
end
