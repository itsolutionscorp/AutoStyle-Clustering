##Hamming Test for Exercism
#@rdarling

class Hamming
	
	def self.compute(string1,string2)
		one = string1.split('')
		two = string2.split('')
		num = one.count
		count = 0
		total = 0
		while count <= num
			if one[count] && two[count]
				if one[count] != two[count]
					total += 1
				end				
			end	
			count += 1		
		end
		total
	end
	
end
