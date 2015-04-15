def compute(sequence1, sequence2)
		
		count = 0;
		
		# Use the length of sequence1 to determine the number of iterations
		for i in 0..sequence1.length
			
			# Compare the chars in each position of the sequences
			if sequence1[i] != sequence2[i]
				count = count + 1
			end
		end
		
		# Return count
		count
    end