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
    start_verse.downto(end_verse).map { |number| verse(number) }.join("\n") + "\n"
  end

  private

  def verse_0
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def verse_1
    "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def verse_2
    "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
  end

  def verse_n(number)
    "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number-1} bottles of beer on the wall.\n"
  end
end
