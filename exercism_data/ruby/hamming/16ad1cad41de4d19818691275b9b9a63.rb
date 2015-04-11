class Hamming #define clas

	def self.compute(strand1, strand2)
		#compare the length of the strings to set the correct bounds for for loop
		if ( strand1.length > strand2.length ) then
			count = strand2.length 
		else
			count = strand1.length
		end

		#just do it #nikeswag
		diff = 0
		count.times do |index|

			if ( strand1[index] != strand2[index] ) then # if they are not equal
				diff += 1 # increment the character difference
			end

		end

		return diff #return the amount of characters that are different

	end

end
