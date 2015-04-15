class Grains

	def square(chessSquare)
		return 2 ** (chessSquare - 1)
	end

	def total
		currentSquare = 1
		maxLimit = 65
		total = 0

		while currentSquare < maxLimit
		   total += square(currentSquare) 
		   currentSquare += 1
		end
		return total
	end
end
