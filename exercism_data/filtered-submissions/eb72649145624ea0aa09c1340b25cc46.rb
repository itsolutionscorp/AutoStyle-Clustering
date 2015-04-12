def compute(sequence_1, sequence_2)
		array1 = sequence_1.split(//)
		array2 = sequence_2.split(//)
		differences = 0
		counter = 0
		unless array1.length == array2.length
			array1.pop
		end		
		array1.each do |i|
			unless i == array2[counter]
				differences += 1
			end
			counter += 1
		end
		differences		
	end