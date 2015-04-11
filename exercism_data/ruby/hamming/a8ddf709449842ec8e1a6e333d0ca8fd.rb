class Hamming
	public
	def self.compute(str1, str2)
		differences = 0
		str1Array = str1.split("")
		str2Array = str2.split("")
		
		shorter = str1.length < str2.length ? str1.length : str2.length
		index = 0
		while index < shorter do
			if str1[index] != str2[index]
				differences += 1
			end
			index += 1
		end		
		
		
		
		return differences
	end
end
