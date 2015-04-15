def compute(sequence1, sequence2)


		minlength = [sequence1.length, sequence2.length].min


		differential = 0


		(0..minlength-1).each do |i|
			if(sequence1[i] != sequence2[i])
				differential += 1
			end
		end


		return differential
	end