class BeerSong
  def verse(num)
    case num
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\n"\
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    when 1
      "1 bottle of beer on the wall, #{pluralize 'bottle', num} of beer.\n"\
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "#{pluralize 'bottle', num} of beer on the wall, #{pluralize 'bottle', num} of beer.\n"\
      "Take one down and pass it around, #{pluralize 'bottle', num-1} of beer on the wall.\n"
    end
  end

  def verses(from, to)
    from.downto(to).map do |num|
      verse(num)
    end.join("\n")
  end

  def sing
    verses(99, 0)
  end

private

  def pluralize(str, num)
    "#{num} #{str}#{'s' unless num == 1}"
  end
end
