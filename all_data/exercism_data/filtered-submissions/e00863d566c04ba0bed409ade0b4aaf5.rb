def compute(string1, string2)
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