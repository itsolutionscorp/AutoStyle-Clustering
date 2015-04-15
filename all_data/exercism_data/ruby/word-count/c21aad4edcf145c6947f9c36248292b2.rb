class Phrase

  def initialize(phrase)
 	  @phrase = phrase.to_s
 	end

 	def word_count
		@word_count ||= parse
 	end

	private
	
	def parse
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
