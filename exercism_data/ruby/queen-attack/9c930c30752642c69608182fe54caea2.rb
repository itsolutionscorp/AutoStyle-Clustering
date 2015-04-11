class Queens

  def initialize (positions = {})
    @board = Array.new(8) { Array.new(8) {"O"} }
    @white_pos = positions[:white] || [0,3]
    @black_pos = positions[:black] || [7,3]
    set_board
    raise ArgumentError if @white_pos == @black_pos
  end

  def white
    @white_pos
  end

  def black
    @black_pos
  end

  def attack?
    @white_pos[0] == @black_pos[0] ||
      @white_pos[1] == @black_pos[1] ||
      attack_diagonal?
  end

  def attack_diagonal?
    row = 0
    col = @black_pos[1] + @black_pos[0]
    until row == 8 do
      return true if same_pos?([row,col],@white_pos)
      row += 1
      col -= 1
    end
    row = 0
    col = @black_pos[1] - @black_pos[0]
    until row == 8 do
      return true if same_pos?([row,col],@white_pos)
      row += 1
      col += 1
    end
    false
  end

  def same_pos?(pos1, pos2)
    pos1 == pos2
  end


  def to_s
    @board.map do |row|
      row.join(" ")
    end.join("\n")
  end


  private

  def set_board
    @board[@white_pos[0]][@white_pos[1]] = "W"
    @board[@black_pos[0]][@black_pos[1]] = "B"
  end
end
