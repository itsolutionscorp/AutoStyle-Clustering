class Grains

  @@chessboard = {1=>1}
  (1..64).each{|x| @@chessboard[x] ||= 2*@@chessboard[x-1]}
  
  def square(val)
    @@chessboard[val]
  end

  def total
    sum = 0
    @@chessboard.each{|key, value| sum += value}
    sum
  end

end
