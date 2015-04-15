class Scrabble
	total = 0
	def initialize(word)
		@word = word
	end

	def self.score(a)
		self.new(a).score
	end

	def score
		(@word.nil? || @word.empty?) ? 0 : @word.downcase.split(//).map{|b| points(b)}.inject(0){ |result, element| result + element }
	end

	def points(letter)
	case letter
	when 'a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'
		1
	when 'd', 'g'
		2
	when 'b', 'c', 'm', 'p'
		3
	when 'f', 'h', 'v', 'w', 'y'
		4
	when 'k'
		5
	when 'j', 'x'
		8
	when 'q', 'z'
		10
	else
		0
	end

  end

end
