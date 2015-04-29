class Scrabble

	def initialize(word)
		@word = word
	end

	def score
		self.class.calculate(@word)
	end

	def self.score(word)
		calculate(word)
	end

	private

	def self.calculate(word)
		return 0 if word.nil? || word.strip.empty?
		score = 0
		word.split("").each do |letter| 
			score += self.score_list[letter.downcase]
		end
		score
	end


	def self.score_list
		list = {}
		list.merge!(Hash[%W(a e i o  u l n r s t).map{|e| [e, 1 ] }])
		list.merge!(Hash[%W(d g).map {|e| [e, 2] }])
		list.merge!(Hash[%W(b c m p).map {|e| [e, 3 ] }])
		list.merge!(Hash[%W(f h v w y).map {|e| [e, 4 ] }])
		list.merge!(Hash["k", 5])
		list.merge!(Hash[%W(j x).map {|e| [e, 8 ] }])
		list.merge!(Hash[%W(q z).map {|e| [e, 10 ] }])
	end
end
