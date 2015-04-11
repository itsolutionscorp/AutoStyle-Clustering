class Hamming 

	def self.compute(strand1, strand2)
		if strand1 == strand2
			return 0		
		else
			self.complex_compute(strand1,strand2)
		end 
	end


	def self.complex_compute(strand1, strand2)
		#turn both into array iterate through arrays incrementing index, compare results
		array1 = strand1.split("")
		array2 = strand2.split("")
		result = 0 
		i = 0
		while i < strand1.length
			if array1[i] != array2[i]
				result += 1
			end 
			i += 1
		end 
		return result
	end  	
end 


 # a = Hamming.compute("Hello", "Hello")

 # a
