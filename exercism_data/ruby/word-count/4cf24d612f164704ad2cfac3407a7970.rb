class Phrase

	def initialize(input)
		@phrase_string = input 
	end

	def word_count
		word_hash = Hash.new
		std_phrase = normalize(@phrase_string)
		std_phrase.split.each { |word|
			word_hash[word] = word_hash[word] ? word_hash[word] + 1 : 1
		}
		
		word_hash
	end
	
	private

	def normalize(orig_phrase)
		orig_phrase.gsub!(/,/, ' ')
		orig_phrase.gsub!(/[!:;.@#$%^&*]/,'')
		orig_phrase.downcase!
		orig_phrase	
	end

end
