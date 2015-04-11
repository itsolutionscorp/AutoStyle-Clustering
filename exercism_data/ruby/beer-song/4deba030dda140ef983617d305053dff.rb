class BeerSong
  ZERO_VERSE = "No more bottles of beer on the wall, no more bottles of beer.\n" +
               "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

  def raw_verse(number)
    "#{number} bottles of beer on the wall, #{number} bottles of beer.\n" +
    "Take one down and pass it around, #{number - 1} bottles of beer on the wall.\n"
  end

  def verse(number)
    return ZERO_VERSE if number == 0
    fixed_verse = raw_verse(number).gsub("1 bottles", "1 bottle").gsub("0 bottles", "no bottles")
    fixed_verse.sub!("one", "it").sub!("no bottles", "no more bottles") if number == 1
    fixed_verse
  end

  def verses(num1, num2)
    (num2..num1).map { |num| verse(num) + "\n" }.reverse.join
  end

  def sing
    verses 99, 0
  end
end
