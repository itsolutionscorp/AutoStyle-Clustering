def compute(s1, s2)
		dist = 0



		[s1.length, s2.length].min.times do |i|
			unless s1[i] == s2[i]
				dist += 1
			end
		end

		return dist

	end