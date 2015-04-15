class Grains

	def square num
		board = [1, 2]
		return num if num <= 2
		3.upto(num) do |square|
			board << board[-1]*2
		end
		board[-1]
	end

	def total 
		board = [1, 2]
		3.upto(64) do |square|
			board << board[-1]*2
		end
		result = 0
		board.each do |num|
			result += num
		end
		result
	end

end
