class String
  def pluralize(number)
    number == 1 ? to_s : "#{to_s}s"
  end
end

class BeerSong
  def verse(number)
    if (number.to_i == 0)
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif (number.to_i == 1)
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "#{number} #{"bottle".pluralize(number)} of beer on the wall, #{number} #{"bottle".pluralize(number)} of beer.\nTake one down and pass it around, #{number-1} #{"bottle".pluralize(number-1)} of beer on the wall.\n"
    end
  end

  def verses(end_verse_number, start_verse_number)
    verses_to_sing = (start_verse_number..end_verse_number).to_a
    verses = verses_to_sing.collect do |verse_number|
      verse(verse_number)
    end
    verses.reverse.join("\n") + "\n"
  end

  def sing
    verses(99,0)
  end
end
