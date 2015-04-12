class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		@word_hash = Hash.new
		subbed_phrase = @phrase.gsub!( /[!&@\$%\^&:\.]*/, "").squeeze(" ")
		p subbed_phrase
		
		subbed_phrase.gsub!( /\s+/n, " ")
		split_phrase = subbed_phrase.split(/[\s\,]+/)
		split_phrase.each do |word|
			if !@word_hash.has_key? (word.downcase)
				@word_hash[word.downcase] = 1
			else
				@word_hash[word.downcase] += 1
			end
		end
		@word_hash
	end

	def word_hash
		@word_hash
	end
	def phrase
		@phrase
	end
		
	
end
