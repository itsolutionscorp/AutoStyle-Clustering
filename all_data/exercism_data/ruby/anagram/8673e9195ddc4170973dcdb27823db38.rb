class Anagram
	def initialize(word)
		@word = word.downcase
	end

	def match(pretenders)
		pretenders.select do |pretender|
			pretender = pretender.downcase
			pretender != @word && anagram?(pretender)
		end
	end

	private

	def anagram?(pretender)
		pretender.chars.sort == @word.chars.sort
	end
end
