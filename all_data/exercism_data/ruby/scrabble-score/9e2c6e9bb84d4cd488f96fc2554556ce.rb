class Scrabble

	def initialize(word)
		!word.is_a?(String) ? @word = '' : @word = word.gsub(/[^a-zA-Z]/, '').upcase
	end

	def score
		sum = 0
		values = Hash.new
		['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'].each { |letter| values[letter] = 1 }
		['D', 'G'].each { |letter| values[letter] = 2 }
		['B', 'C', 'M', 'P'].each { |letter| values[letter] = 3 }
		['F', 'H', 'V', 'W', 'Y'].each { |letter| values[letter] = 4 }
		values['K'] = 5
		['J', 'X'].each { |letter| values[letter] = 8 }
		['Q', 'Z'].each { |letter| values[letter] = 10 }

		@word.each_char do |letter|
			sum += values[letter] if values.has_key? letter
		end
		sum
	end

	def self.score(word)
		new(word).score
	end

end
