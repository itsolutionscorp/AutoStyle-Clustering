class Robot
	attr_reader :name

	def initialize
		@name = ''
		@name << (0..1).map{ (65 + rand(26)).chr }.join
		@name << (rand(900) + 100).to_s
	end

	def reset
		new_name = ''
		new_name << (0..1).map { (65 + rand(26)).chr }.join
		new_name << (rand(900) + 100).to_s
		@name = new_name
	end
end
