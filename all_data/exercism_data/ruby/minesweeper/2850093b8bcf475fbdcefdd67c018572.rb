class Board

	def self.transform(board)
		validate board

		middle = board.each_cons(3).map do |rows|
			transform_row(rows)	
		end

		[board[0], *middle, board[-1]]
	end

	private

		def self.transform_row(rows)
			rows[1].chars.map.with_index do |char, i|
				char == " " ? count_mines(i, rows) : char
			end.join('')
		end

		def self.count_mines(i, rows)
			mines = 0
			rows.each do |row|
				[-1, 0, 1].each do |offset|
					mines += 1 if row[i+offset] == "*"
				end
			end
			mines == 0 ? " " : mines
		end

		def self.validate(board)
			border = [board.first, board.last]
			middle = board[1..(board.length-2)]

			raise ValueError unless same_size_rows?(board)
			raise ValueError if border.any? {|row| row =~ /[^+-]/  }
			raise ValueError if middle.any? {|row| row =~ /[^* |]/ }
		end

		def self.same_size_rows?(board)
			board.all? { |row| row.size == board.first.size }
		end
end

class ValueError < StandardError; end
