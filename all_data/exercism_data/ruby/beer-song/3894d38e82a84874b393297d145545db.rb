class Beer
  def verse(number)
    case number
    when 0 then verse_0
    when 1 then verse_1
    when 2 then verse_2
    else verse_n(number)
    end
  end

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).map { |number| verse(number) + "\n" }.join
  end

  private

  def verse_0
    "No more bottles of beer on the wall, no more bottles of beer.\n" +
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def verse_1
    "1 bottle of beer on the wall, 1 bottle of beer.\n" +
    "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def verse_2
    "2 bottles of beer on the wall, 2 bottles of beer.\n" +
    "Take one down and pass it around, 1 bottle of beer on the wall.\n"
  end

  def verse_n(number)
    count, next_count = number, number.pred
    "#{count} bottles of beer on the wall, #{count} bottles of beer.\n" +
    "Take one down and pass it around, #{next_count} bottles of beer on the wall.\n"
  end
end
