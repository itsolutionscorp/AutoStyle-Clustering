class Phrase

	def initialize (phrase)
		@phrase = phrase.downcase
		@hash = {}
	end

	def word_count
		word_list = @phrase.scan(/[\w']+/).uniq
		word_list.each do |word|
			@hash[word] = @phrase.scan(/\b#{word}\b/).count
		end
		@hash
	end

end
