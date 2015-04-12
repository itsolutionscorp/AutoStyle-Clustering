class Phrase
	def initialize(word)
		@word = word
	end
	
	def word_count
		words.reduce({}) do |hsh, word|
			hsh[word] ||= 0
			hsh[word] += 1
			hsh
		end
	end
	
	def words
		@word.split(/[\!\@\#\$\%\^\&\*\(\)\:\_, ]/)
			 .map{|w| w.downcase.strip}
			 .reject &:empty?
	end
end
