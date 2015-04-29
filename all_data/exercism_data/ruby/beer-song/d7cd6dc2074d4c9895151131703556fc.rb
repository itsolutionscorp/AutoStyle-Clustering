=begin
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.

No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
=end

class BeerSong
  def verse(n)
    if n > 0
      "#{ n } bottle#{ 's' if n > 1 } of beer on the wall, #{ n } bottle#{ 's' if n > 1 } of beer.\n" +
      "Take #{ n-1 == 0 ? 'it' : 'one' } down and pass it around, #{ n-1 > 0 ? n-1 : 'no more' } bottle#{ 's' if n-1 > 1 || n-1 == 0 } of beer on the wall.\n"
    else
      "No more bottles of beer on the wall, no more bottles of beer.\n" +
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def verses(last,first)
    (first..last).to_a.reverse.map { |n| verse(n) }.join("\n") + "\n"
  end

  def sing
    verses(99,0)
  end
end
