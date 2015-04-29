class BeerSong

  def verse(count)
    "#{first_part(count)}#{second_part(count)}"
  end

  def verses(from, to)
    from.downto(to).inject("") do |song, count|
      "#{song}#{verse count}\n"
    end
  end

  def sing
    verses 99, 0
  end

private

  def first_part(count)
    "#{x_bottles count} of beer on the wall, #{x_bottles count} of beer.\n".capitalize
  end

  def second_part(count)
    if count == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "Take #{one_bottle count} down and pass it around, #{x_bottles (count - 1)} of beer on the wall.\n"
    end
  end

  def x_bottles(count)
    case count
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{count} bottles"
    end
  end

  def one_bottle(count)
    case count
    when 1 then "it"
    else "one"
    end
  end

end
