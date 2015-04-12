class Phrase
	def initialize(string)
		@string = string
	end

	def word_count
		hash = Hash.new(0)
		@string.downcase.scan(/[a-z0-9']+/) do |word|
			hash[word] += 1
		end
		hash
	end
end

phrase = Phrase.new("one fish two fish red fish blue fish")
phrase.word_count
