class Queens
  
  attr_accessor :white, :black
  
  def initialize(args=nil)
    unless args
      self.white = [0, 3]
      self.black = [7, 3]
    else
      raise ArgumentError if args[:white] ==  args[:black]
      self.white = args[:white]  
      self.black = args[:black]
    end  
  end
  
  def to_s
    board = <<-BOARD.chomp
O O O O O O O O
O O O O O O O O
O O O O O O O O
O O O O O O O O
O O O O O O O O
O O O O O O O O
O O O O O O O O
O O O O O O O O
    BOARD
    white_loc = self.white[0]*8*2+self.white[1]*2
    black_loc = self.black[0]*8*2+self.black[1]*2
    board[white_loc] = "W"
    board[black_loc] = "B"
    board
  end  
  
  def attack?
    across? || down? || diagonal? || diagonal_other_way?
  end
  
  def across?
    self.white[0] == self.black[0]
  end
  
  def down?
    self.white[1] == self.black[1]
  end    
  
  def diagonal?
    self.white[0] - self.white[1] ==  self.black[0] - self.black[1]
  end
  
  def diagonal_other_way?
    self.white[0] + self.white[1] == self.black[0] + self.black[1]
  end
end
