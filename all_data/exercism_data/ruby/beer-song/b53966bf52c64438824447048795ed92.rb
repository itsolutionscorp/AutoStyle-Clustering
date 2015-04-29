class Beer

  def sing higher_verse, lower_verse=0
    verses_sung = []
    higher_verse.downto(lower_verse) do |verse_number|
      verses_sung << verse(verse_number)
    end
    verses_sung.join("\n") << "\n"
  end

  def verse verse_number
    case verse_number
    when 0 then "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    when 1 then "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    when 2 then "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    else
      "#{verse_number} bottles of beer on the wall, #{verse_number} bottles of beer.\nTake one down and pass it around, #{verse_number - 1} bottles of beer on the wall.\n"
    end
  end

end
