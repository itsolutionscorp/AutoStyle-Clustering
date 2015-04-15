class Hamming 
	def self.compute(str1, str2)
		
		count = 0

		if str1.length < str2.length
			count = strdiff(str1, str2) 
		else 
			count = strdiff(str2, str1)
		end

	end

	def self.strdiff(str1, str2)
		str1arr = str1.split('') #the shorter string
		str2arr = str2.split('') #the longer string
		count = 0

		str1arr.each_with_index do |char, index|
			if str1arr[index] != str2arr[index]
				count += 1
			end
		end
		count
	end
end
