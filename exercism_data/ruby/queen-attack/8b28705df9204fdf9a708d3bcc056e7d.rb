require 'matrix'

class Queens
  attr_reader :white,:black
  def initialize white: [0,3], black: [7,3]
    fail ArgumentError if white == black
    @white = white
    @black = black
  end
  
  def attack?
    same_row? ||
    same_col? ||
    is_diagonal?
  end
  
  def to_s
    board.to_a.map{|row| row.join(" ") }.join("\n")
  end
  
  private
  def same_row?
    white[0] == black[0]
  end
  
  def same_col?
    white[1] == black[1]
  end
  
  def is_diagonal?
    (white[0]-black[0]).abs == (white[1]-black[1]).abs
  end
  
  def board
    @board ||= Matrix.build(8){|row,col| cell_val row, col}
  end
  
  def cell_val row,col
    case [row,col]
    when white
      "W"
    when black
      "B"
    else
      "O"
    end
  end
end
