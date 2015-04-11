class Beer
  def verse(line)
    line == 0 ? final_verse : default_verse(line)
  end

  def sing(start, ending = 0)
    song = ""

    start.downto(ending) do |line|
      song << song_verse(line)
    end

    song
  end

  private

  def final_verse
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def default_verse(line)
    "#{bottles(line)} of beer on the wall, #{bottles(line)} of beer.\nTake #{pluralizer(line)} down and pass it around, #{bottles(line - 1)} of beer on the wall.\n"
  end

  def song_verse(line)
    verse(line) + "\n"
  end

  def pluralizer(line)
    line == 1 ? "it" : "one"
  end

  def bottles(quantity)
    case quantity
    when 0
      "no more bottles"
    when 1
      "#{quantity} bottle"
    else
      "#{quantity} bottles"
    end
  end
end
