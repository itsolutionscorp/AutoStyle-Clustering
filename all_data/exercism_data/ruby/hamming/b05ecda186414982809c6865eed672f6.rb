class Hamming

	def self.compute(string1, string2)
		hamming=0
		arr1=string1.split('')
		arr2=string2.split('')
		arr1.each_with_index do |letter,i|
			if arr1[i] != arr2[i]
				if arr1[i]!=nil && arr2[i] !=nil
				hamming +=1
				end
			end
		end
		hamming
	end

end
