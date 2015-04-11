class Hamming
	def self.compute(seq1, seq2)
		arr1 = seq1.split("")
		arr2 = seq2.split("")
		hamming_distance = 0

		if arr1.length <= arr2.length
			length = arr1.length
		else arr1.length > arr2.length
			length = arr2.length
		end

		(0..length-1).each do |x|
			hamming_distance += 1 if arr1[x] != arr2[x]
		end

	hamming_distance
	end
end
