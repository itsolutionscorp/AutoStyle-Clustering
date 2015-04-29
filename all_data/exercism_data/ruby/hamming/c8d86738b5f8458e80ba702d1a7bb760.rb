class Hamming
	def self.compute(sequence1, sequence2)
		
		# Get the length of the smaller of the two arrays
		minlength = [sequence1.length, sequence2.length].min
		
		# Declare the differential couners
		differential = 0
		
		# Loop thruogh each character of the string and add +1 if the two characters are not the same
		(0..minlength-1).each do |i|
			if(sequence1[i] != sequence2[i])
				differential += 1
			end
		end
		
		# Return the result
		return differential
	end
end
