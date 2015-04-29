class Queens

  attr_reader :white
  attr_reader :black

  def initialize(*args)
    if args.empty?
      @white = [0, 3]
      @black = [7, 3]
    else
      raise ArgumentError if args.first[:white] == args.first[:black]
      @white = args.first[:white]
      @black = args.first[:black]
    end
  end

  def internal_board
    internal_board = [["O","O","O","O","O","O","O","O"],
                      ["O","O","O","O","O","O","O","O"],
                      ["O","O","O","O","O","O","O","O"],
                      ["O","O","O","O","O","O","O","O"],
                      ["O","O","O","O","O","O","O","O"],
                      ["O","O","O","O","O","O","O","O"],
                      ["O","O","O","O","O","O","O","O"],
                      ["O","O","O","O","O","O","O","O"]]
    internal_board[@white.first][@white.last] = "W"
    internal_board[@black.first][@black.last] = "B"
    internal_board
  end

  def to_s
    board = ''
    internal_board.each do |row|
      board << row.join(' ') + "\n"
    end
     board.strip
  end

  def attack?
    @white.first == @black.first || 
    @white.last == @black.last ||
    (@white.first - @black.first).abs == (@white.last - @black.last).abs
  end

end
