class Hamming
	# def self.match(nucleo1, nucleo2)
	# 	if (nucleo1 == 'A' && nucleo2 == 'T') || (nucleo1 =='C' && nucleo2 =='G') || (nucleo1 == 'T' && nucleo2 == 'A') || (nucleo1 == 'G' && nucleo2 == 'C')
	# 		0
	# 	else 1
	# 	end
	# end

	def self.compute(string1, string2)
		arr1 = string1.split("")
		arr2 = string2.split("")
		i = 0
		count = 0
		arr1.each do |nucleo|
			if arr1[i] == nil || arr2[i] == nil
				count = count + 0
			elsif arr1[i] != arr2[i]
				count = count + 1
			end
			i = i + 1
		end
		return count
	end
end
