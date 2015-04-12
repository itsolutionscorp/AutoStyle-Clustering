def compute(string1, string2)
		str1arr = string1.split ""
		str2arr = string2.split ""
		retval = 0

		str1arr.each_index {|i|
			if (str1arr[i] != str2arr[i])
				retval += 1
			end
		}

		return retval
	end