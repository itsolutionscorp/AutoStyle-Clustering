class Beer
  def verse(line)
    if line == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{bottles_pluralizer(line)} of beer on the wall, #{bottles_pluralizer(line)} of beer.\nTake #{pluralizer(line)} down and pass it around, #{bottles_pluralizer(line - 1)} of beer on the wall.\n"
    end
  end

  def sing(ending, start = 0)
    song  = ""
    lines = (start..ending).to_a.reverse

    lines.each do |line|
      song << verse(line) + "\n"
    end

    song
  end

  private

  def pluralizer(line)
    if line == 1
      "it"
    else
      "one"
    end
  end

  def bottles_pluralizer(bottles)
    if bottles > 1
      "#{bottles} bottles"
    elsif bottles == 1
      "#{bottles} bottle"
    else
      "no more bottles"
    end
  end
end
