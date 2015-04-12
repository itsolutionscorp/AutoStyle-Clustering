class HammingTest
	def hamming(string1, string2)
		string1_letters = string1.split('')
		string2_letters = string2.split('')
		hamming_distance = 0

		# If the two strands of DNA are the same length
		if string1_letters.length == string2_letters.length
			count = 0

			#  While count is less than the total number of elements in the array
			while count < string1_letters.length

				# Add 1 to the hamming distance if the element in the first string 
				# does not equal the same indexed element in the second string
				hamming_distance += 1 if string1_letters[count] != string2_letters[count]
				count += 1
			end
		end

		puts "The hamming distance of these two sequences is: #{hamming_distance}"
	end
end
