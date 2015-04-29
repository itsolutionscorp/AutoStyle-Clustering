class Robot
	#attr_reader :name
	def initialize 
		@name = nil
	end
	def name
		if @name.nil?
			@name = Robot.generate
		end
		return @name
	end
	def reset
		old_name = @name
		begin
			@name = Robot.new.name
		end until @name!=old_name
	end
	private
	def self.generate
		new_name = String.new
		new_name= Random.random_letter + 
							Random.random_letter +
							Random.random_digit  +
							Random.random_digit  +
							Random.random_digit  +
							Random.random_digit
		return new_name
	end
end
class Random
	def self.random_letter
		return (rand(26)+65).chr
	end
	def self.random_digit
		return (rand(10)).to_s
	end	
end
