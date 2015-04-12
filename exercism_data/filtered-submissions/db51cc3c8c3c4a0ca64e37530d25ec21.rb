def compute(string_a, string_b)
		counter = 0
		iterator = 0
		while iterator < [string_a.length, string_b.length].min
			if string_a[iterator] != string_b[iterator]
				counter+=1
			end
			iterator+=1
		end
		counter
	end