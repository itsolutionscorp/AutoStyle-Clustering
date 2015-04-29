class Queens

  attr_reader :white, :black

  def initialize positions = {:white=>[0, 3], :black=>[7, 3]}
    @white = positions[:white]
    @black = positions[:black]
    raise ArgumentError if white == black
    @board = []
    (0..7).each {|x| @board[x] = (0..7).each.with_object([]) {|y, z| z[y] = '_'}}
    @board[white[0]][white[1]] = 'W'
    @board[black[0]][black[1]] = 'B'
  end

  def to_s
    @board.each_index {|b| @board[b] = @board[b].join(' ')}
    str = ''
    @board.each {|b| str << b + "\n"}
    str.chomp
  end

  def attack?
    white[0] == black[0] || white[1] == black[1] || \
    white[1] - white[0] == black[1] - black[0] || white[1] + white[0] == black[1] + black[0]
  end

end
