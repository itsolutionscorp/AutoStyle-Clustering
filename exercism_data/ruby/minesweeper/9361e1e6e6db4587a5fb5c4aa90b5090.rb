class ValueError < StandardError; end

class Board
  
  class << self
    def transform(from)

      raise ValueError unless valid?(from)

      board = from.map(&:chars)
      (0...board.count).map.with_index do |row, row_idx|
        (0...board[0].count).map.with_index do |cell, col_idx|
          count_mines(board, row_idx, col_idx)
        end.join
      end
    end
    
  private

    def count_mines(board, r, c)
      return board[r][c] if board[r][c] != " "
      environment(board, r, c).count("*").to_s.gsub(/0/, ' ')
    end

    def environment(board, r, c)
      top = [0, r-1].max
      bottom = [board.length, r+1].min
      left = [0, c-1].max
      right = [board[0].length, c+1].min
      (top..bottom).to_a.inject("") do |result, rownum|
        result + board[rownum].join.slice(left..right)
      end
    end
    
    def valid?(input)
      same_length?(input) && valid_border?(input)
    end
    
    def same_length?(input)
      input.map(&:length).uniq.count == 1
    end
    
    def valid_border?(input)
      valid_top_bottom?(input) && valid_left_right?(input)
    end
    
    def valid_top_bottom?(input)
      width = input[0].length
      correct = "+" + "-" * (width-2) + "+"
      input[0] == correct && input[-1] == correct
    end
    
    def valid_left_right?(input)
      width = input[0].length
      input[1..-2].count do |row|
        row !~ /\|[\*\s]{#{width-2}}\|/ 
      end == 0
    end

  end
end
