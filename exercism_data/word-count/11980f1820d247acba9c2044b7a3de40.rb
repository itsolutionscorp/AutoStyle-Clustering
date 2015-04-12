class Phrase

	def initialize(word)
		@word = word
	end

	def sanitize
		@san = @word.gsub(',',' ').gsub(/[^\w\s](?<!')/, "").downcase
	end

	def word_count
		sanitize
		count = Hash.new(0)
		@san.split.each { |w| count[w] += 1 }
		count
	end
end
