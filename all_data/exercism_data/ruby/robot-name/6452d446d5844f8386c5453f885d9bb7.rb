class Robot
	attr_accessor :name

	def initialize
		reset
	end

	def reset
		letters = (('a'..'z').to_a + ('A'..'Z').to_a)
		letters_name = "#{letters[rand(52)]}#{letters[rand(52)]}"

		numbers = 3.times.map{ Random.new.rand(0..9)}.join
		@name = "#{letters_name}#{numbers}"
	end

end
