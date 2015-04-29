class Robot
	attr_reader :name

	def initialize
		@name = generate_name
	end

	def reset
		@name = generate_name
	end

	def generate_name
		name = ""
		2.times { name << (65+rand(26)).chr }
		3.times { name << rand(10).to_s }
		name
	end
end
