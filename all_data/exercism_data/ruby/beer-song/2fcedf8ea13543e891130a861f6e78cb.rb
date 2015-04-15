class Beer  
  VERSE_TEMPLATE = "%d bottles of beer on the wall, %d bottles of beer.\nTake one down and pass it around, %d %s of beer on the wall.\n"
  LAST_VERSE = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  RESTART_VERSE = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

  def verse(verse_number)
    find_verse(verse_number.to_i)
  end

  def sing(start_verse, end_verse = 0)
    get_verses(start_verse.to_i, end_verse.to_i)
  end
  
  private
  
  def find_verse(verse_number)
    case 
      when verse_number > 1 
      format_template(verse_number)
      when verse_number == 1 
      last_verse
      when verse_number == 0 
      song_restart
    end
  end

  def get_verses(start_verse, end_verse)
    [*end_verse..start_verse].reverse.map{|verse_number| "%s\n"%[verse(verse_number)] }.join
  end

  def format_template(verse_number)
    if verse_number > 2
      VERSE_TEMPLATE%[verse_number, verse_number, verse_number - 1, "bottles"]
    else
      VERSE_TEMPLATE%[verse_number, verse_number, verse_number - 1, "bottle"]
    end
  end
  
  def last_verse
    LAST_VERSE
  end
  
  def song_restart
    RESTART_VERSE
  end
end
