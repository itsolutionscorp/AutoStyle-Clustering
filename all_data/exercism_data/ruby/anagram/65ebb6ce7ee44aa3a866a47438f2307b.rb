class Anagram
	def initialize(word)
      @word = word
	end

	def match(options = [])
		matches = []
		options.each do |option|
			if @word.downcase.split('').sort.join == option.downcase.split('').sort.join && option.downcase != @word.downcase 
              matches << option
			end

		end
		return matches
	end
end
