class Beer
  def sing(start_verse, end_verse=0)
    start_verse.downto(end_verse).map {|v| verse(v)}.join("\n") + "\n"
  end

  def verse(bottles)
    "#{bottle_phrase(bottles).capitalize} on the wall, #{bottle_phrase(bottles)}.\n" +
    "#{take_one_phrase(bottles)}, #{bottle_phrase(bottles-1)} on the wall.\n"
  end

  def bottle_phrase(bottles)
    case bottles
    when 0 then "no more bottles of beer"
    when 1 then "1 bottle of beer"
    when -1 then "99 bottles of beer"
    else "#{bottles} bottles of beer"
    end
  end

  def take_one_phrase(bottles)
    case bottles
    when 0 then "Go to the store and buy some more"
    when 1 then "Take it down and pass it around"
    else "Take one down and pass it around"
    end
  end
end
