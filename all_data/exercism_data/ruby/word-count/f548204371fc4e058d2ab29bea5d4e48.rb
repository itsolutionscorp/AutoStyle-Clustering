class Phrase
	attr_accessor :phrase

	def initialize(str)
		@phrase = str
	end

	def word_count
		a = self.to_a_of_words
		a.inject({}) { |res, w| res.merge(w => a.count(w)) }
	end

	def to_a_of_words
		res = []
		word = ""
		phrase.downcase.each_char do |c|
			if c =~ /\w/
				word << c
			else
				res << word unless word.empty?
				word = ""
			end
		end
		res << word unless word.empty?
		res
	end
end
