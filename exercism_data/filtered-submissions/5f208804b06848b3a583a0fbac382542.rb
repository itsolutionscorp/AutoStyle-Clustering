def compute(string1, string2)
		differenceCounter =0

		for i in (0... [string1.length, string2.length].min )
			if string1[i] != string2[i]
				differenceCounter +=1
			end
		end
		differenceCounter
	end