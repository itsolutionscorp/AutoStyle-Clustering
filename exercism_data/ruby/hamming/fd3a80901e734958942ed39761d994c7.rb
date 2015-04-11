class Hamming
	def self.compute(strand1,strand2)
		hamming_diff = 0
		letter_arr1 = strand1.split('')
		letter_arr2 = strand2.split('')

		[letter_arr1.length, letter_arr2.length].min.times do |i|
			if letter_arr1[i] != letter_arr2[i]
				then hamming_diff += 1
			end
		end
		
		return hamming_diff
	end
end
