require 'securerandom'

class Robot
	attr_accessor :name

	def initialize
		setup
	end

	def reset
		setup
	end

	private

	def setup
		rand_num = SecureRandom.random_number(1000)
		rand_str = (0...2).map { (65 + rand(26)).chr }.join
		@name = rand_str + rand_num.to_s
	end
end
