class Robot
	attr_reader :name
	@@names = []

	def initialize
		new_name
	end

	def reset
		new_name
	end
	
	private
	def new_name
		@name = generate_name
		@name = generate_name while @@names.include? @name
		@@names << @name
		@name
	end
	def generate_name
		alphabet = ("A".."Z").to_a
		[alphabet[rand(25)], alphabet[rand(25)], rand(9), rand(9), rand(9)].join("")
	end
end
