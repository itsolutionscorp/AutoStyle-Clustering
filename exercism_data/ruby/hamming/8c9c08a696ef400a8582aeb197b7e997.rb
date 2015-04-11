class Hamming


	def self.compute(string1, string2)
		hammingDist = 0
		
		arr1= string1.split('')
		arr2= string2.split('')

		#finds which is the largest of the two arrays
		if arr2.length > arr1.length
			largeArr = arr2
			smallArr=arr1
		else #includes case that both are an equal length
			largeArr = arr1
			smallArr=arr2
		end

		#compares the char values at each index 'i'
		#when unequal, add 1 to hammingDist
		smallArr.length.times do |i|
			if !self.charEqual(smallArr[i], largeArr[i])
				hammingDist += 1
			end
		end

		return hammingDist

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


