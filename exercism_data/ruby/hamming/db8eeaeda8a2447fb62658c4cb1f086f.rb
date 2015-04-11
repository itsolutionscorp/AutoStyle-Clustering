class Hamming

	def compute(string1,string2)
		if string1.length < string2.length
			length = string1.length
		else
			length = string2.length
		end
		count = 0
		(0..(length-1)).each{|x|
			count +=1 if string1[x] != string2[x]
		}
		count
	end

end
