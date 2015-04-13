def compute(s1, s2)
		counter = 0

		([s1.length, s2.length].min).times do |count|
			if s1[count] != s2[count]
				counter += 1
			end
		end

		counter

	end