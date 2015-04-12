def compute(input1, input2)
	counter = 0
	(0..[input1.length, input2.length].min - 1).each do |i|
		if (input1[i] != input2[i])
			counter = counter + 1
		end
	end
	return counter
end