#Add Hamming module
class Hamming
	#Add compute method
	def self.compute (strand1, strand2)
		#Init number of dissimilarities (dissim) and length of strand (length)
		index = 0
		dissim = 0
		length = strand1.length

		#Loop through each character (nucleobase) in each strand and check if dissimilar.
		#Incriment dissim if both characters in the same index (of strand1 and strand2) are not equal.
		while index != length do
			dissim += 1 unless strand1[index] == strand2[index]
			index += 1
		end
		dissim
	end
end
