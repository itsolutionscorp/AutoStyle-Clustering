# Create Hamming module
class Hamming
	# Create compute method
	def self.compute (strand1, strand2)
		# Iterate through each nucleobase in each strand and check if equal
		# If not equal, increment dist to count dissimilarities
		i = 0
		dist = 0

		while i != strand1.length do
			dist += 1 unless strand1[i] == strand2[i]
			i += 1
		end

		return dist
	end
end
