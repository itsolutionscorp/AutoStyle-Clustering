def compute(strand1,strand2)
		s1 = strand1
		s2 = strand2
		diff = 0
		complength = 0
		if s1.length < s2.length
			complength = s1.length
		else
			complength = s2.length
		end
		i = 0
		for i in 0...complength
			if s1[i] != s2[i]
				diff += 1
			end
		end
		puts diff
		return diff
	end