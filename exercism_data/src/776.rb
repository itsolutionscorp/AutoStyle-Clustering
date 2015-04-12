class Phrase

	def initialize(phrase)
		@phrase = phrase
	end
	
	def word_count
		hash = {}
		# arr = @phrase.scan(/[^\s,;:\/-_!&@$%^&?]+/)
		arr = @phrase.scan(/[a-zA-Z0-9']+/)
		
		arr.each do |word|
			if hash.member?(word.downcase)
				hash[word.downcase] += 1
			else
				hash.merge!(word.downcase => 1)
			end		
		end
		hash
	end

end 
