def compute(arg1, arg2)

		counter = 0
		output = 0

		arg1.split("").each do |char|

			output+=1 if char != arg2[counter]
			counter+=1

		end

		output

	end