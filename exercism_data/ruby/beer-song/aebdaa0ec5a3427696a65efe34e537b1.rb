class Beer
  def sing(start, finish=nil)
    finish ||= 0
    verses = []
    start.downto(finish) { |beer_count| verses << verse(beer_count) }
    verses.join("\n") + "\n"
  end

  def verse(beer_count)
    if beer_count == 0
      verse_0
    elsif beer_count == 1
      verse_1
    else
      regular_verse(beer_count)
    end
  end

  private

  def regular_verse(beer_count)
    remaining = beer_count - 1
    <<-VERSE
#{beer_count} bottles of beer on the wall, #{beer_count} bottles of beer.
Take one down and pass it around, #{remaining} #{bottle_text(remaining)} of beer on the wall.
       VERSE
  end

  def verse_1
    <<-VERSE
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
       VERSE
  end

  def verse_0
    <<-VERSE
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
       VERSE
  end

  def bottle_text(count)
    count == 1 ? "bottle" : "bottles"
  end
end
