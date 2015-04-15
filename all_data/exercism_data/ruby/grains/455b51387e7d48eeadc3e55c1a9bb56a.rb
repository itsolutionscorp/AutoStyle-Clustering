class Grains
  def square(n)
    board = []
    n.times{|x|
      board << 2**x
    }
    board[n-1]
  end

  def total
    2**64-1
  end
end

#p Grains.new.square(2)
