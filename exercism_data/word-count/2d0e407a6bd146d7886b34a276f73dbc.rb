class Phrase
	attr_accessor :word_count

	def initialize(aPhrase)
		@word_count = { }

		aPhrase.tr("^A-Za-z0-9'", " ").split.each { |x|
			x.downcase!

			@word_count.has_key?(x) ? @word_count[x] += 1 : @word_count[x] = 1
		}
	end
end
