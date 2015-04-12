class Phrase
	def initialize(string)
		@string = string
	end

	def word_count
		punctuation = [',','.','?','!','@','&','%','$','^',':']

		@string.each_char do |x|
			if punctuation.include?(x)
				@string[x] = " "
			end
		end

		words = @string.downcase.split(" ")
		dictionary = {}

		words.each do |x|
			if dictionary[x] == nil
				dictionary[x] = 0
			end
			dictionary[x] += 1
		end
		dictionary
	end
end
