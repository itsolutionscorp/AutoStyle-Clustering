class Phrase

  def initialize(phrase)
 	  @phrase = phrase.to_s
 	end

 	def word_count
		@word_count ||= count_words_in_phrase
 	end

	private
	
	def count_words_in_phrase
		tokenize(normalize(@phrase)).each_with_object(Hash.new(0)) { |word, result| 
			result[word] += 1
		}
	end

	def normalize(str)
		str.downcase
	end

	def tokenize(str)
		str.scan(/\w+/)
	end
end
