class Anagram

	def initialize(string)
		@string = string.downcase
	end
	
	
	def char_count_array(string)
		hash = {}
		if string then
			arr = string.each_char.to_a		
			arr.each do |char|
				if hash.has_key?(char)
					hash[char] += 1
				else hash.merge!(char => 1)
				end
			end
		end	
		hash
	end
	

	def match(arr)
		string = @string
		result = []	
		
		arr.each do |word|
			if self.char_count_array(word.downcase) == self.char_count_array(@string) && word.downcase != @string
				result << word
			end
		end
		
		result	
	end

end
