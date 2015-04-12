def compute(str1, str2)
		counter=0
		min_length = [str1.length, str2.length].min
		i=0
		while i<min_length do
			counter++ if not str1[i] == str2[i]
			i = i+1
		end
		counter
	end