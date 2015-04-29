class BeerSong
  VERSE_TEMPLATE    = ["%d bottles of beer on the wall, %d bottles of beer.\n",
                       "Take one down and pass it around, %d bottle%s of beer on the wall.\n"].join
  PENULTIMATE_VERSE = ["1 bottle of beer on the wall, 1 bottle of beer.\n",
                       "Take it down and pass it around, no more bottles of beer on the wall.\n"].join
  FINAL_VERSE       = ["No more bottles of beer on the wall, no more bottles of beer.\n",
                       "Go to the store and buy some more, 99 bottles of beer on the wall.\n"].join

  def sing
    verses(99)
  end

  def verses(starting, ending=0)
    starting.downto(ending).map {|number| "#{verse(number)}\n" }.join
  end

  def verse(number)
    case number
    when 0
      FINAL_VERSE
    when 1
      PENULTIMATE_VERSE
    else
      VERSE_TEMPLATE%[number, number, number - 1, number == 2 ? '' : 's' ]
    end
  end
end
