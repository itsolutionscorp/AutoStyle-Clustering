class Beer
  def verse(num)
    remaining = num - 1
    case
    when num > 1
      "#{num} bottles of beer on the wall, #{num} bottles of beer.\n" +
      "Take one down and pass it around, #{quantified(remaining, 'bottle')} of beer on the wall.\n"
    when num == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\n" +
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "No more bottles of beer on the wall, no more bottles of beer.\n" +
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end

  end

  def sing(verse_high, verse_low=0)
    verse_high.downto(verse_low).map do |verse|
      verse(verse) + "\n"
    end.join
  end
  
  private

  def quantified(quantity, noun)
    "#{quantity} #{noun}#{'s' if quantity != 1}"
  end

end
