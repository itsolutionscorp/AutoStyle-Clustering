class Phrase
	def initialize(arg)
		word = arg.downcase.gsub(/[^\w']+/, ' ')
		@words = word.split(' ')
	end 
	def word_count
		p @words
		@totalcount = Hash.new
		@words.each do |words|
			@count = @words.count(words)
			@totalcount[words] = @count
			p @totalcount
		end
		return @totalcount
	end
end
