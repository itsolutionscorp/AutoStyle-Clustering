class Beer
  VERSE_0 = "No more bottles of beer on the wall, no more bottles of beer.\n" +
            "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  VERSE_N_A = "%d %s of beer on the wall, %d %s of beer.\n"
  VERSE_N_B = "Take %s down and pass it around, %s %s of beer on the wall.\n"

  def verse(count)
    case count
    when 0
      VERSE_0
    else
      sprintf(VERSE_N_A, count, pluralise(count), count, pluralise(count)) +
      sprintf(VERSE_N_B, (count > 1 ? 'one' : 'it'),
              (count == 1 ? 'no more' : count - 1), pluralise(count - 1))
    end
  end

  def sing(start, stop = 0)
    start.downto(stop).each_with_object([]) do |n, verses|
      verses.push verse(n)
    end.join("\n") + "\n"
  end

  private

  def pluralise(count)
    count == 1 ? 'bottle' : 'bottles'
  end
end
