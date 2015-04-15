class Hamming

	def self.compute(val1, val2)
		val1 = clean_data(val1)
		val2 = clean_data(val2)
		count = 0
		val1.size.times do |i|
			count +=1 if val2[i] && val2[i] != val1[i]
		end
	
		return count
		
	rescue RuntimeError
		puts "Bad Data"
	end
 
	
	def self.clean_data(val)
		val = val.upcase
		return val if /^[AGCT]+$/ === val
		raise RuntimeError, "Bad Data"
	end

end
