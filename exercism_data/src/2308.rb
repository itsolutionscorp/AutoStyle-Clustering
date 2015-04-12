def compute(string1,string2)
		array1, array2, sum = string1.chars, string2.chars, 0
		while !(array1[0] == nil || array2[0] == nil)
			sum += 1 if array1.shift[0] != array2.shift[0]
		end
		sum
	end