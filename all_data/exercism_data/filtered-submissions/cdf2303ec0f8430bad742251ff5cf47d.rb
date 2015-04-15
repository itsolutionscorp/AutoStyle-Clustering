def compute(strand1,strand2)

		compare = (strand1.split("")).zip(strand2.split(""))

		distance = 0
		compare.each do |pair|
			if pair[0] == pair[1] or pair[1] == nil
				distance += 0
			elsif pair[0] != pair[1]
				distance += 1
			end
		end

		distance

	end