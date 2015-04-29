class Beer

  def sing(start, finish=nil)
    finish ||= 0
    start.downto(finish).reduce("") do |song, beer_count|
      song + verse(beer_count) + "\n"
    end
  end

  def verse(count)
    case count
    when 0; verse_0
    when 1; verse_1
    else; verse_x(count)
    end
  end

  private

  def verse_x(count)
    remaining = count - 1
    "#{beer_text(count).capitalize} on the wall, #{beer_text(count)}.\nTake one down and pass it around, #{beer_text(remaining)} on the wall.\n"
  end

  def verse_1
    "#{beer_text(1).capitalize} on the wall, #{beer_text(1)}.\nTake it down and pass it around, #{beer_text(0)} on the wall.\n"
  end

  def verse_0
    "#{beer_text(0).capitalize} on the wall, #{beer_text(0)}.\nGo to the store and buy some more, #{beer_text(99)} on the wall.\n"
  end

  def beer_text(count)
    case count
    when 0; "no more bottles of beer"
    when 1; "1 bottle of beer"
    else; "#{count} bottles of beer"
    end
  end
end
