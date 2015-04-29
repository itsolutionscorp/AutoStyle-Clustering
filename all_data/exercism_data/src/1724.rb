def compute(str1, str2)
		difference = 0
		(0...str1.length).each do |x|
			next if !str2[x]
			difference +=1 if str1[x]!=str2[x]
		end
		return difference
	end