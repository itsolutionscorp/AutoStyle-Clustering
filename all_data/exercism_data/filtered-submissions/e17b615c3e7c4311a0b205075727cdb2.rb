def compute(s, t)

		return 0 if s === t



		seq_one = s.scan /\w/
		seq_two = t.scan /\w/
		differences = 0
		character_count = (seq_one.size < seq_two.size) ? seq_one.size : seq_two.size


		while character_count > 0 do
			tmp_one = seq_one.shift
			tmp_two = seq_two.shift

			differences += 1 if tmp_one != tmp_two

			character_count -= 1
		end

		differences
	end