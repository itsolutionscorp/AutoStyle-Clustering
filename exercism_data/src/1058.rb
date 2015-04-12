class Hamming
	def compute(strand1, strand2)
		zips = strand1.chars.zip strand2.chars

		zips.select! { |item| item[0] != item[1] }

		zips.length
	end
end
