class Phrase
	def initialize(sentense)
		# gsub => selecting only letters and numbers
		@words = sentense.downcase.gsub(/[^a-zA-Z1-9]/, ' ').split
	end

	def word_count
		word_count = {}
		@words.each do |word|
			word_count[word] = (word_count[word].nil? ? 1 : word_count[word] + 1) unless word.nil? or word.empty?
		end
		word_count
	end
end
