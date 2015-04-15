class Hamming
	def self.compute(strand1, strand2)
		arr1 = strand1.split("")
		arr2 = strand2.split("")

		counter = 0
			arr1.each_with_index do |character, index|
				unless arr2[index] == character
					counter += 1
				end
			end
		counter
	end
end
