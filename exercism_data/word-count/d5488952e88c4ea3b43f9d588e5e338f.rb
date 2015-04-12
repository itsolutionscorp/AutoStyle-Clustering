class Phrase
	def initialize apple
		@apple = apple.downcase.gsub(/[']/, '_')
	end
	def word_count
		the_words = @apple.split(/\W+/)
		hashy = {}

		the_words.each do |word|
			word.gsub!(/[_]/, '\'')
			if hashy[word] == nil
				hashy[word] = 1
			elsif
				hashy[word] += 1
			end
		end
		hashy
	end
end
