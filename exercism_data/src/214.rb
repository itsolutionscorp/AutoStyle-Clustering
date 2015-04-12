def compute(string1,string2)
		if string1.length < string2.length
			a = string1
			b = string2
		else
			a = string2
			b = string1
		end
		count = 0
		(0..(a.length-1)).each{|x|
			count +=1 if a[x] != b[x]
		}
		count
	end