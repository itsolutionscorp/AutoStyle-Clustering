class Phrase
	def initialize(phrase)
		@phrase = phrase.delete(":,!&@$%^")
										.downcase
										.split
	end
	def word_count
 		@phrase.each_with_object({}) do |word, words|
 			words[word] ||= 0
 			words[word] += 1
 		end


 		#@phrase.downcase.split.inject(Hash.new(0)) { |h,v| h[v] += 1; h }
	end
end
