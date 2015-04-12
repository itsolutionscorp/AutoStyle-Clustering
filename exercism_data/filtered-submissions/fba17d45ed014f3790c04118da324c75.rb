#!/usr/bin/ruby

module Hamming

	# Computers Hamming difference of two strans of DNA
	# Params:
	# +stran1+ stran of DNA to compare
	# +stran2+ stran of DNA to compare
	# Return: Int, Hamming Distance
	def Hamming.compute(stran1, stran2)
		i = 0
		len = 0
		dist = 0
		# Split strans into array to loop over
		stran_array1 = stran1.split("")
		stran_array2 = stran2.split("")  
		
		# test to see what stran is shorter to use for loop
		if stran1.length < stran2.length
			len = stran1.length
		else
			len = stran2.length
		end

		# loop through shortest stran
		while i < len
			# compare DNA segment, if differ add to dist
			if stran_array1[i] != stran_array2[i]
				dist += 1
			end
			
			i += 1
		end

		return dist
	end
end
