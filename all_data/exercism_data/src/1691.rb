def compute test1, test2
		return 0 if test1 == test2 
		split_test1 = test1.split("")
		split_test2 = test2.split("")
		counter = 0
		split_test1.each_with_index do |item, index| 
			if item != nil && split_test2[index] != nil
				counter += 1 if item != split_test2[index] 
			end
		end
		counter
	end