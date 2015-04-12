class Phrase
	def initialize(string)
		@word_array = string.scan(/[\w']+/).map(&:downcase)
	end
	def word_count
		count = Hash.new(0)
		@word_array.each { |word| count[word] += 1}
		count
	end
end
