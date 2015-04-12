class Phrase
	def initialize (str)
		@str = str
	end

	def word_count
		count_hash = Hash.new{0}

		@str.scan(/[\w']+/).each do |word|
			count_hash[word.downcase] += 1
		end

		count_hash
	end
end
