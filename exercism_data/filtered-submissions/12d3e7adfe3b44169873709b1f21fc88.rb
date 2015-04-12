def compute(s1, s2)
	distance = 0
	for i in 0..(s1.length)
		distance+=1 unless s1[i]==s2[i]
	end
	distance
 end