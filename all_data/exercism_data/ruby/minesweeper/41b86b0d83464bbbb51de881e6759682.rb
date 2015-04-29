require 'set'

class ValueError < StandardError; end

class Board
	@@valid_tokens = Set.new('+-|* '.chars)
	
	def self.transform(board)
		raise(ValueError, 'Invalid board size!') if board.any?{|r| r.length != board[0].length}
		raise(ValueError, 'Invalid token on board!') if board.any?{|r| r.chars.any?{|c| !@@valid_tokens.include?(c)} }
		raise(ValueError, 'Invalid token placement!') if board.first.include?('*') || board.last.include?('*')
		raise(ValueError, 'Invalid token placement!') if (1..board.size-2).any?{|i| board[i][0] == '*' || board[i][-1] == '*'}
		
		board = board.collect{|r| r.split('')}
		board.each_with_index do |row, r|
			row.each_with_index do |v, c|
				if v == '*'
					(-1..1).each do |r_adj|
						(-1..1).each do |c_adj|
							board[r + r_adj][c + c_adj] = adjust(board[r + r_adj][c + c_adj])
						end
					end
				end
			end
		end
		
		return board.collect{|r| r.join}
	end
	
	private
	
	def self.adjust(current)
		return current if current !~ /[[:digit:] ]/ 
		(current.to_i + 1).to_s
	end
end
