def compute(strand1, strand2)
			distance = 0

			[strand1.length, strand2.length].min.times do |i|
				if strand1[i] != strand2[i]
					distance += 1
				end
			end

			distance
		end