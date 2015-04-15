class Scrabble
	          #   A B C D E F G H I J K L M N O P Q  R S T U V W X Y Z
	@@SCORES = %w{1 3 3 2 1 4 2 4 1 8 5 1 3 1 1 3 10 1 1 1 1 4 4 8 4 10}

	def initialize(word)
		@word = word
	end

	def score
		return @word.to_s.upcase.each_byte.map {|c| @@SCORES[c - 65].to_i}.inject(:+).to_i
	end

	def self.score(word)
		return self.new(word).score
	end
end
