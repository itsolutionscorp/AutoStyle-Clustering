class Beer

  def sing(start, last = 0)
    verses = start.downto(last).collect do |number|
      verse number
    end

    verses.join("\n") + "\n"
  end

  def verse(number)
    case
    when number == 0
      verse_0
    when number == 1
      verse_1
    else
      verse_for number
    end
  end

  private

  def bottles(number) 
    case
    when number == 1
      "1 bottle" 
    else
      "#{number} bottles"
    end
  end

  def verse_for(number)
    "#{ bottles(number) } of beer on the wall, #{ bottles(number) } of beer.\nTake one down and pass it around, #{ bottles(number - 1) } of beer on the wall.\n"
  end

  def verse_1
    "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def verse_0
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

end

# Notes
#
# 1. One could use pluralize in active_support/inflector and remove the bottles method, but
#   in this case it's better to not create a dependency.
# 2. I tried heredocs in verse_for, verse_1 and verse_0 but it looked ugly.
