#Add Hamming module
class Hamming
	#Add compute method
	def compute (strand1, strand2)
		#Init index (i) and hamming distance (dist)
		i = 0
		dist = 0

		#Iterate through each character (nucleobase) in each strand and check if equal.
		#Increment dist if characters in the same index (of strand1 and strand2) are not equal.
		while i != strand1.length do
			dist += 1 unless strand1[i] == strand2[i]
			i += 1
		end

		return dist
	end
end
