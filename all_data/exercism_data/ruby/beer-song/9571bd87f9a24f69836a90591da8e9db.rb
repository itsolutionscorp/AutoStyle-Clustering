class Beer

  def sing higher_verse, lower_verse=0
    verses = (lower_verse..higher_verse).to_a.reverse
    verses.map { |a_verse| verse a_verse }.join("\n") << "\n"
  end

  def verse verse_number
    if verse_number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif verse_number == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif verse_number == 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    else
      "#{verse_number} bottles of beer on the wall, #{verse_number} bottles of beer.\nTake one down and pass it around, #{verse_number - 1} bottles of beer on the wall.\n"
    end
  end

end
