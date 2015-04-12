class Phrase
	def initialize arg
		@phrase = arg
	end
	
	def word_count

		word_array = @phrase.gsub(/\W/, ' ').downcase.split(' ')

		hash = Hash.new(0)

		word_array.each do |word|
			hash[word] += 1
		end

		hash
	end
end
