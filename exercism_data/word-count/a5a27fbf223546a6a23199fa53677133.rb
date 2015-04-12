class Phrase
	attr_reader:word_count
	def initialize(str)
		@word_count = Hash.new(0)
		str.gsub(/[^a-zA-Z0-9\']/, ' ').split(/[\s]+/).each do |word|
			@word_count[word.downcase] += 1 
		end
	end
end
