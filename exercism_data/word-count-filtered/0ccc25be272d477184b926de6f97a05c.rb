class Phrase
	def initialize str
		str2 = str.gsub(',', ' ')
		str3 = str2.chars.select{|x| x if x =~ /[\s|\d|\w|']/}.join
		@words = str3.split(' ').map{|x| x.downcase }
		h = {}
		@words.each {|w| h[w] ||= 0; h[w] += 1}
		@counts = h
	end
	def word_count
		@counts
	end
end
