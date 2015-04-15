class Grains
  def square(n)
    2 ** (n - 1)
  end

  def total
    board = [ *0..63]
    result = board.map{ |a| (2**a) }
    result.inject {|sum,n| sum + n }
  end
end
