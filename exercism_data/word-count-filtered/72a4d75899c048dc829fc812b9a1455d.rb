class Phrase
	def initialize(string)
		@wordlist = string.scan(/[\w']+/).map(&:downcase)
	end
	def word_count
		count = Hash.new(0)
		@wordlist.each { |word| count[word] += 1}
		count
	end
end
