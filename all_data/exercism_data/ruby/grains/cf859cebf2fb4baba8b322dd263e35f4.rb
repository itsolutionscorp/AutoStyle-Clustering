class Grains
	def square(board)
		sum = 1
		board_value = 1
		(board-1).times do |num|
			board_value = board_value * 2
		end
	  return board_value
	end

	def total
		sum = 0
		(1..64).each do |board|
		  sum = sum + square(board)
	  end

	  return sum
	end
end
