class Scrabble

	@word
	@one_points
	@two_points
	@three_points
	@four_point
	@five_points
	@eight_points
	@ten_points

	def initialize(word)
		@word = word
		@one_points = ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T']
		@two_points = ['D', 'G']
		@three_points = ['B', 'C', 'M', 'P']
		@four_points = ['F', 'H', 'V', 'W', 'Y']
		@five_points = ['K']
		@eight_points = ['J', 'X']
		@ten_points = ['Q', 'Z']
	end

	def self.score(word)
		scrabble = Scrabble.new(word)
		scrabble.score
	end

	def score
		return 0 if @word.nil? 
		@word.strip!
		return 0 if @word.empty?
		score = 0
		@word.each_char do |char|
			score += get_point(char.upcase)
		end
		score
	end

	private
	def get_point(char)
		return 1 if @one_points.include?(char)
		return 2 if @two_points.include?(char)
		return 3 if @three_points.include?(char)
		return 4 if @four_points.include?(char)
		return 5 if @five_points.include?(char)
		return 8 if @eight_points.include?(char)
		return 10 if @ten_points.include?(char)
	end

end
