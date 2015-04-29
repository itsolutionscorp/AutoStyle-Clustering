class Robot
	attr_reader :name
	
	def initialize
		@name = self.new_name
	end

	def name
		@name
	end

	def reset
		@name = self.new_name
	end

	def new_name
		letters = (0...2).map { (65 + rand(26)).chr }.join
		numbers = (1.times.map { 100 + rand(899) }.to_s).gsub(/[\[\]]/, '')
		@name = letters.to_s + numbers
	end

end
