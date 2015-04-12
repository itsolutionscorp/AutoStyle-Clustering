class Hamming


	def compute(s1, s2)
		dist = 0
		
			#compares the char values at each index 'i'
			#when unequal, add 1 to dist
		[s1.length, s2.length].min.times do |i|
			unless s1[i] == s2[i]
				dist += 1
			end
		end

		return dist

	end


	
end

