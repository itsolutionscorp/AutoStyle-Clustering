class Phrase
	attr_accessor :p

	def initialize(str)
		@p = str
	end

	def word_count
		a = []
		word = ""
		p.downcase.each_char do |c|
			if c =~ /\w/
				word << c
			else
				a << word unless word.empty?
				word = ""
			end
		end
		a << word unless word.empty?
		a.inject({}) { |res, w| res.merge(w => a.count(w)) }
	end
end
