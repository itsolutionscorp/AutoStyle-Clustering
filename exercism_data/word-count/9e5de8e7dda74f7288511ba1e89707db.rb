class Phrase

	def initialize(word)
		@word = word
	end

	def sanitize
		@san = @word.gsub(',',' ').gsub(/[^\w\s](?<!')/, "").downcase
	end

	def word_count
		sanitize
		@san.split.each_with_object(Hash.new(0)) { |k,v| v[k] += 1 }
	end
end
