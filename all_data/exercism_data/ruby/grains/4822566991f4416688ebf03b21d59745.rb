class Grains
	def square(number)
		unless number.nil?
			if number.is_a? Integer
				if number <=64
					if number == 1
						return 1
					else
						return ((2**(number-1)))
					end
				else
					puts "A Chessboard has only 64 squares!"
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
