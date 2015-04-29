class Hamming


	def self.compute(s1, s2)
		dist = 0
		
		# arr1= s1.split('')
		# arr2= s2.split('')

		# #finds which is the largest of the two arrays
		# if arr2.length > arr1.length
		# 	largeArr = arr2
		# 	smallArr=arr1
		# else #includes case that both are an equal length
		# 	largeArr = arr1
		# 	smallArr=arr2
		# end

		#compares the char values at each index 'i'
		#when unequal, add 1 to hammingDist
		[s1.length, s2.length].min.times do |i|
			unless self.charEqual(s1[i], s2[i])
				dist += 1
			end
		end

		return dist

	end




	#return true if two char are equal and return false if unequal
	def self.charEqual (char1, char2)
		if char1 == char2 
			return true
		else
			return false
		end
	end



end


