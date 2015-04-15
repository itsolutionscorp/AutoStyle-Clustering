def combine_anagrams(words)
	output = []
	words.each do |x|
		exist = false
		outputAux = Array.new(output)
		output.each do |y|
			if y[0].downcase.split(//).sort == x.downcase.split(//).sort
				outputAux.delete(y)					
				outputAux<<(y<<x)
				exist = true
			end
		end
		output = outputAux
		if exist == false
			output << [x]
		end
	end
	return output
end
