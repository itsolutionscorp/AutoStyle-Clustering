def compute(string1,string2)
		strand1 = string1.upcase()
		strand2 = string2.upcase()
		if ((strand1 == strand2)|| (strand1.length != strand2.length))
			return 0
		else
			hamming_distance = 0
			(0..(strand1.length)).each do |index|
				hamming_distance+=1 if strand1[index] != strand2[index]
			end
			return hamming_distance
		end
	end