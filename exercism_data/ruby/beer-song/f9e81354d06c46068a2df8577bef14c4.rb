module NumberHelpers
  def quantified(quantity, noun)
    singular = quantity == 1
    "#{quantity} #{noun}#{'s' unless singular}"
  end
end

class Beer
  include NumberHelpers

  def verse(num)
    remaining = num - 1
    case
    when num > 1
      quantified_bottles = quantified(remaining, 'bottle')
      "#{num} bottles of beer on the wall, #{num} bottles of beer.\n" +
      "Take one down and pass it around, #{quantified_bottles} of beer on the wall.\n"
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
  
end
