class BeerSong
  def verse num
    @bottles = num
    return go_to_store if out_of_bottles?
    total_verse = behold
    total_verse << take_down
  end

  def verses (start,finish)
    total_song = ""
    @bottles = start
    until @bottles < finish
      total_song << verse(@bottles) + "\n"
      @bottles -= 1
    end
    total_song
  end

  def sing
    verses(99,0)
  end

  private

  def behold
    "#{pluralizer(@bottles)} of beer on the wall, #{pluralizer(@bottles)} of beer.\n"
  end

  def take_down
    unless last_bottle?
      "Take one down and pass it around, #{pluralizer(@bottles-1)} of beer on the wall.\n"
    else
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    end
  end

  def go_to_store
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def out_of_bottles?
    @bottles == 0
  end

  def last_bottle?
    @bottles == 1
  end

  def pluralizer num
    if num > 1
      "#{num} bottles"
    else
      "#{num} bottle"
    end
  end
end
