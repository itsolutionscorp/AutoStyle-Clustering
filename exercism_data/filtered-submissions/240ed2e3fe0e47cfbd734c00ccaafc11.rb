def compute( seq1, seq2 )
		diff = 0

    seq1.length.times do |n|
      diff += 1 unless seq1[n] == seq2[n]
    end

    diff
	end