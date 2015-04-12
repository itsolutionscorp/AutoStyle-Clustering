class Phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def prep(phrase)
		phrase.gsub!(/[^\d\w']/," ")
		phrase.split(' ').group_by {|word| word.downcase }
	end

	def word_count
		Hash[self.prep(@phrase).map {|word, count| [word, count.size]}]
	end	
end
