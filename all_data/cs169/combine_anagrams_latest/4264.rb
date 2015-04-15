
def combine_anagrams(words)
	@anagram = nil
	words.each do |w|
		if w.length > 0 
			@sorted_string = w.downcase.chars.sort.join
			if (@anagram == nil)
				@anagram = {@sorted_string => [w]}
			else
				if (@anagram.has_key?(@sorted_string))
					@anagram[@sorted_string] << w
				else
					@anagram[@sorted_string] = [w]
				end
			end
		end

	end 

	@array  = Array.new
	@anagram.each_key do |key|
		@array <<  @anagram[key]
	end

	return @array
end