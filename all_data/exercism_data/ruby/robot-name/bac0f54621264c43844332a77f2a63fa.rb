require 'debugger'

class Robot

	attr_accessor :name

	def initialize
		@name = Robot.name
	end

	def reset
		self.name = Robot.name
	end

	def self.name
		letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
		numbers = '1234567890'
		name = ''
		2.times do
			name << letters[rand(letters.length)]
		end
		3.times do
			name << numbers[rand(numbers.length)]
		end
		name
	end

end
