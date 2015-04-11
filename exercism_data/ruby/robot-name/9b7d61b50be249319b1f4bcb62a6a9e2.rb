class Robot
	attr_reader :name
	@@robot_names = []

	def initialize
		@name = generate_unique_name
	end

	def reset
		@@robot_names.delete(@name)
		@name = generate_unique_name
	end

	def generate_unique_name
		unique = false
		until unique
			name = ""
			2.times { name << (65+rand(26)).chr }
			3.times { name << rand(10).to_s }
			unique = !(@@robot_names.include?(name))
		end
		@@robot_names << name
		name
	end

	def self.robot_names
		@@robot_names
	end
end
