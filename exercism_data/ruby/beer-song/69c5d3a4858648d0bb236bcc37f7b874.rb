class Beer

  def verse(n)

    many = "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, #{ n - 1} bottles of beer on the wall.\n"
    two = "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    one = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    zero = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

    n > 2 ? many : n == 2 ? two : n == 1 ? one : zero

  end	

  def sing(n, i = 0)
    song = n.downto(i).map { |n| verse(n) }.join("\n") + "\n"
  end

end
