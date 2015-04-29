class Phrase
	attr_reader :word_count
	def initialize phrase
		@phrase = phrase.downcase.gsub(/[^a-z0-9',\s]/, '')
		@word_count = word_count_hash
	end
	
	private
	def word_count_hash
		phr_ar = @phrase.split(/[\s,]/)
		phr_ha = {}
		phr_ar.each do |word|
			if phr_ha.include?(word)
				phr_ha[word] += 1
			elsif !word.empty?
				phr_ha[word] = 1
			end
		end
		phr_ha
	end
end
