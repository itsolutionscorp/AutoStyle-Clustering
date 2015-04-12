class Phrase
	def initialize str
		str.gsub! ',', ' '
		str2 = str.chars.select{|x| x if x =~ /[\s|\d|\w|']/}.join
		@words = str2.split(' ').each{|x| x.downcase! }
		h = {}
		@words.each {|w| h[w] ||= 0; h[w] += 1}
		@counts = h
	end
	def word_count
		@counts
	end
end
