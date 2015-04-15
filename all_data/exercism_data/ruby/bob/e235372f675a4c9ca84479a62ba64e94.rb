class Bob
	def hey(phrase)
		remark = Remark.new(phrase)
		return "Fine. Be that way!" if remark.silent?
		return "Whoa, chill out!" if remark.yelling?
		return "Sure." if remark.question?
		"Whatever."
	end

	private
	class Remark 
		def initialize(phrase)
			@phrase = phrase
		end

		def yelling?
			@phrase == @phrase.upcase && @phrase.match(/[[:alpha:]]/)
		end

		def question?
			@phrase.end_with?('?')
		end

		def silent?
			@phrase.strip.empty?
		end
	end
end
