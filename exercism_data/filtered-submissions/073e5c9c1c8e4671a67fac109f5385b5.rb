def compute(sequence1, sequence2)


		minlength = [sequence1.length, sequence2.length].min


		differential = 0


		(0..minlength-1).each do |i|
			differential += 1 unless sequence1[i] == sequence2[i]
		end


		return differential
	end