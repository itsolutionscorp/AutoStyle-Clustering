class Phrase
	def initialize(subPhrase)
		@words = subPhrase.split(/[\s,]+/)
		(0..@words.length-1).each do |i|
			@words[i] = @words[i].downcase.gsub(/[^a-z0-9'\s]/i, '')
		end
		@words.delete_if { |content| content == ''}
	end

	def word_count
		wordCount = Hash.new
		@words.each do |w|
			if wordCount[w].nil?
				wordCount[w] = 1
			else
				wordCount[w] = wordCount[w] + 1
			end
		end
		return wordCount
	end
end

