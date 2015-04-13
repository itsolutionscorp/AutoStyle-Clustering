def compute(x, y)
 		count = 0
 		string1 = x.chars
 		string2 = y.chars

	 		string1.each_with_index do |letter, index|


		 			if string2[0..-1] != string1[0..-1]
		 				count += 1

		 			end

	 			end

	 		count
 	end