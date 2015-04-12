def compute(dna_one, dna_two)
			count = 0
			dna_two_arr = dna_two.split('')
		    dna_one.split('').each_with_index do |c, index|
				count += 1 unless c == dna_two_arr[index]
			end
			count
		end