class Queens

  def initialize(white: [0, 3], black: [7, 3])
    @white = white
    @black = black
    raise ArgumentError if white == black
  end
  attr_reader :white, :black

  def white
    @white
  end

  def black
    @black
  end

  def to_s
    Queens.new.board_builder do |row, col|
      add_pieces(row, col)
    end
  end

  def attack?
    dy = (white[0] - black[0]).abs
    dx = (white[1] - black[1]).abs
    white[0] == black[0] || white[1] == black[1] ? true : dy == dx
  end

  def board_builder
    (0..7).map do |row|
      (0..7).map do |col|
        yield row, col
      end.join(' ')
    end.join("\n")
  end

  def add_pieces(row, col)
    case [row, col]
      when white then 'W'
      when black then 'B'
      else '_'
    end
  end
end
