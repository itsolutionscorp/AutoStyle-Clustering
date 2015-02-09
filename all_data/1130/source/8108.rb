def combine_anagrams(words)
	
	a = Array.new
	
	words.each do |word|
		
		test = word.downcase.split(/\w/).sort
		
		c = words.collect do |x|
			
			if x.downcase.split(/\w/).sort == test
			
				x
			
			end
			
			a.push(c)
			
		end
			
		words.delete_if { |y| y.downcase.split(/\w/).sort == test}
		
	end
	
	return a
	
end