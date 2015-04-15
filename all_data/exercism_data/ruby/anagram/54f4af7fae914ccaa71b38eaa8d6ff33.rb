class Anagram
	def initialize(word)
		@word = word
		@result_list = []
	end

	def match(word_list)
		word_list.each do |item|
			if @word.length == item.length
				words_counted = letter_count(@word)
				items_counted = letter_count(item)
				if words_counted == items_counted
					@result_list.push item
				end
			end
		end
		@result_list
	end

	def letter_count(word)
		word.split("").inject({}) do |counter, letter|
			counter[letter] ||= 0
			counter[letter] += 1
			counter
		end
	end
end
