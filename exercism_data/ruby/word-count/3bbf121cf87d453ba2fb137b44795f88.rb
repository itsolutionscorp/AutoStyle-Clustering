class Phrase

	def initialize word
		@word = word
	end

	def word_count
		result = Hash.new(0)
		if @word.include?(' ')
			@word.gsub!(/[^\w\s']/, "")
			@word.downcase.split(" ").each { |words| result[words] += 1 }
			p result
		else
			@word.gsub!(/[^\w\s',]/, "")
			@word.downcase.split(",").each { |words| result[words] += 1 }
			p result
		end
	end

end
