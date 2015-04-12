class Hamming

	def Hamming.compute (a,b)
		
		hamming_distance = 0
		bpos = 0
		
		a.each_char do |char|
			unless char == b[bpos]
				hamming_distance +=1
			end

			bpos += 1

			if b[bpos] == nil
				break
			end	
		end	
		
		hamming_distance
	end

end
