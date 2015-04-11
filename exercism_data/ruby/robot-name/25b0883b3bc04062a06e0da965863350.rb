class RobotFactory

	@robot_names = ["RS0000"]

	def self.create
		begin
      @robot_name = self.generate_robot_name
    end while @robot_names.include? @robot_name
		@robot_names << @robot_name
		@robot_name
	end

	def self.reset
		@robot_names[0]
	end

	def self.generate_robot_name
		name = generate_random_prefix
		name << generate_random_sufix
	end

	def self.generate_random_sufix
		number = rand(9999).to_s
		(4 - number.size).times do
		  number << "0"			
		end
		number.reverse
	end

	def self.generate_random_prefix
		(0..1).map{ alphabet_letters[rand(alphabet_letters.length)] }.join
	end

	def self.alphabet_letters
		('A'..'Z').to_a
	end

end

class Robot
	attr_reader :name
	def initialize
		@name = RobotFactory.create
	end

  def reset
  	@name = RobotFactory.reset
  end
end
