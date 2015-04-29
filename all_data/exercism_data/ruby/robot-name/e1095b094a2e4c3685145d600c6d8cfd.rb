require 'set'

class Robot
	attr_reader :name

	@@used = Set.new

	def initialize
		@name = generate_name
	end

	def reset
		@name = generate_name
	end

	def generate_name
		begin 
			candidate = generate_random_name
		end while @@used.add?(candidate)
		return candidate
	end

	def generate_random_name
		return (0..1).map{ ('A'..'Z').to_a[rand(26)] }.join + (0..2).map{ ('0'..'9').to_a[rand(10)] }.join
	end
end
	
