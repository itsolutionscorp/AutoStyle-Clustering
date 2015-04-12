class Phrase < String

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		word_hash = Hash.new
		word_array = @phrase.downcase.scan(/\w+'*\w*/)
		word_array.uniq.each do |x|
			word_hash.store(x, word_array.count(x))
		end
		return word_hash
	end

end
