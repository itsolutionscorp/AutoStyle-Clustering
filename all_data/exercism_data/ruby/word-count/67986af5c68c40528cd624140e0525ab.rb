class Phrase
	attr_reader :word_count

	def initialize ( phrase )
		@word_count = Hash.new
		phrase.scan(/[\w']+/).each do | word |
			word = word.downcase
			if @word_count.has_key?( word ) then
				@word_count[word] += 1
			else
				@word_count[word] = 1
			end
		end

	end
end
