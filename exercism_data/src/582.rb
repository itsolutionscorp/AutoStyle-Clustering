class Phrase
	attr_accessor :text

	def initialize(str)
		@text = str
	end
	
	def word_count
		result = Hash.new	
		@text.split(/\W/).reject(&:empty?).each do |word|
			word.downcase!
			result[word] = result[word].nil? ? 1 : result[word] + 1
		end
		result
	end
end
