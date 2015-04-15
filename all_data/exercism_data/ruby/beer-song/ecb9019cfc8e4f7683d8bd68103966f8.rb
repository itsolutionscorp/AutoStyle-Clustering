class BeerSong
  LINES = Hash.new(
    "%{some} bottles of beer on the wall, %{some} bottles of beer.\n" \
      "Take one down and pass it around, %{fewer} bottles of beer on the wall.\n"
  ).merge({
    2 => "%{some} bottles of beer on the wall, %{some} bottles of beer.\n" \
      "Take one down and pass it around, %{fewer} bottle of beer on the wall.\n",
    1 => "%{some} bottle of beer on the wall, %{some} bottle of beer.\n" \
      "Take it down and pass it around, no more bottles of beer on the wall.\n",
    0 => "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n",
  }).freeze

  def verse(n)
    LINES[n] % { some: n, fewer: n - 1 }
  end

  def verses(start = 99, finish = 0)
    start.downto(finish).collect do |n|
      verse(n) + "\n"
    end.join
  end

  def sing
    verses
  end

end
