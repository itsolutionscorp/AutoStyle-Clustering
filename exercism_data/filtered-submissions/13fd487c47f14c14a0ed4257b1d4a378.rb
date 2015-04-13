def compute(strand1, strand2)

		if strand1 == strand2

			0
		else


			hamming_distance = 0



			for i in 0..strand1.length - 1

				if strand1[i] != strand2[i]
					hamming_distance += 1
				end

			end

			hamming_distance

		end
	end