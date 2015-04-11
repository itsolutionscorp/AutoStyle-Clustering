class Robot
	attr_reader :name
	attr_reader :previous_names
	def initialize
		@name = generate_name
		@previous_names = []
	end

	def reset
		previous_names << name
		@name = generate_name
		reset if previous_names.include? @name 
	end

	def generate_name
		name = letter + letter + (0..2).map {|i| rand(9)}.join
		

	end

	def letter
		(65 + rand(26)).chr
	end
end
