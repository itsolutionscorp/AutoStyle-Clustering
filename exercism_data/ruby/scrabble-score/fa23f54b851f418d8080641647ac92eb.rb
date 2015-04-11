class Scrabble
	def initialize(word)
		word ||= ""
		@word = word
	end

	POINTS = {
		1 => ["A", "E", "I", "O", "U", "L", "N", "R", "S", "T"],
		2 => ["D", "G"],
		3 => ["B", "C", "M", "P"],
		4 => ["F", "H", "V", "W", "Y"],
		5 => ["K"],
		8 => ["J", "X"],
		10 => ["Q", "Z"]
	}

	def score
		self.calculate_score(@word)
	end

	def self.score(word)
		self.calculate_score(word)
	end

	def self.calculate_score(word)
		letters = extract_letters(word)
		@total_score = 0
		letters.each do |letter|
			POINTS.each do |key, value|
				@total_score = @total_score + key if value.include? letter
			end
		end
		@total_score
	end

	def calculate_score(word)
		letters = extract_letters(word)
		@total_score = 0
		letters.each do |letter|
			POINTS.each do |key, value|
				@total_score = @total_score + key if value.include? letter
			end
		end
		@total_score
	end

	def self.extract_letters(word)
		word.upcase.split(//)
	end

	def extract_letters(word)
		word.upcase.split(//)
	end
end
