class Robot
	attr_accessor :name

	def initialize
		@name = generate_name
	end

	def reset
		@name = ""
	end

	def name
		return @name unless @name.empty?
		generate_name
	end

	def generate_name
		@name = (0...2).map { (65 + rand(26)).chr }.join + rand(999).to_s
	end
end
