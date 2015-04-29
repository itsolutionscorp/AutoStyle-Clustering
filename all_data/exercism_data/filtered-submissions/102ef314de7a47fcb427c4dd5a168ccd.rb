def compute string1, string2
		count = 0
		index = 0
		while index != string1.length do
			if not string1[index].chomp.eql? string2[index].chomp
				count += 1
			end
			index += 1
		end
		count
	end