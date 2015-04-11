class Grains
	def square(number)
		unless number.nil?
			if number.is_a? Integer
				if number <=64 && number >= 1
					if number == 1
						return 1
					else
						return ((2**(number-1)))
					end
				else
					puts "Please enter a number from 1 to 64!"
				end
			else
				puts "Invalid argument"
			end
		else
			puts "Please provide the number of the square to test"
		end
	end
	def total
		return ((2**64) - 1)
	end
end
