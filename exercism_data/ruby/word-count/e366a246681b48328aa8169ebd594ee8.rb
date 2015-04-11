class Phrase
	attr_reader :words
	def initialize(words)
		@words = words
	end

	def word_count
		words.gsub(/[^a-z0-9',\s]/i, '').split(/,| /).each{ |w| w.downcase!}.inject(Hash.new(0)) { |h,w| h[w] += 1; h }.tap{ |hs| hs.delete('')}
	end
end
