class ValueError < StandardError
end

class Board
  def initialize
    @board = []
  end

  def self.transform input
    Board.new.transform input
  end

  def transform input
    fail ValueError unless input.map(&:length).uniq.size == 1
    fail ValueError if input.first.match(/[^+-]/) || input.last.match(/[^+-]/)

    @board = input.map do |line|
      line.chars.select { |c| c.match /[^\+\|-]/ }
    end[1..-2]

    fail ValueError if @board.any? { |line| line.join.match /[^* ]/ }

    @board.each_with_index do |line, i|
      line.map!.with_index do |char, j|
        char == '*' ? char : "#{map_mines i, j}"
      end
    end

    @board.map! { |line| "|#{line.join}|" }
          .map! { |line| line.gsub('0', ' ') }

    borders = "+#{'-' * (@board.first.length - 2)}+"
    @board.unshift(borders).push(borders)
  end

  private

  def minus v
    v - 1 < 0 ? nil : v - 1
  end

  def map_mines i, j
    a_l = @board[minus i][minus j] rescue nil
    a_c = @board[minus i][j]       rescue nil
    a_r = @board[minus i][j + 1]   rescue nil
    l   = @board[i][minus j]       rescue nil
    r   = @board[i][j + 1]         rescue nil
    b_l = @board[i + 1][minus j]   rescue nil
    b_c = @board[i + 1][j]         rescue nil
    b_r = @board[i + 1][j + 1]     rescue nil
    [a_l, a_c, a_r, l, r, b_l, b_c, b_r].count { |cell| cell == '*' }
  end
end
