class Beer
  def verse(line)
    line == 0 ? final_verse : default_verse(line)
  end

  def sing(start, ending = 0)
    song = ""

    start.downto(ending) do |line|
      song << verse(line) + "\n"
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

  def pluralizer(line)
    line == 1 ? "it" : "one"
  end

  def bottles(quantity)
    if quantity > 1
      "#{quantity} bottles"
    elsif quantity == 1
      "#{quantity} bottle"
    else
      "no more bottles"
    end
  end
end
