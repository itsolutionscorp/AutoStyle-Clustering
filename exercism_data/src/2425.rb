class Hamming

	def compute(strA, strB)
		count = 0
		ldiff = 0
		range = 0
		ldiff = strA.length - strB.length
		
		if ldiff > 0 
			range = strB.length - 1
			strA = strA[0..range]
		end

		strA.chars.each_with_index do |c, i|
			if strB[i] == strA[i]
			else
				count += 1
			end
		end
		count
	end

end
