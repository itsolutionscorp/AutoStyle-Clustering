def compute(str1, str2)
		if str1.length < str2.length
			i = str1.length
		else
			i = str2.length
		end
		x = 0
		dif = 0
		while(x != i)
			if(str1[x] != str2[x])
				dif += 1
			end
			x += 1
		end
		return dif
	end