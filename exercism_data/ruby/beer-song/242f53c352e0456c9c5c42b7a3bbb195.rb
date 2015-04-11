module PluralizeBottle
  def bottles 
    case self
    when 0
      return "no more bottles"
    when 1
      return "1 bottle"
    else
      return "#{self} bottles"
    end
  end

  def take_one_down
    case self
    when 1
      return "Take it down"
    else
      return "Take one down"
    end
  end
end
Fixnum.send :include, PluralizeBottle


class BeerSong
  def verse n
    lyrics = "#{n.bottles} of beer on the wall, #{n.bottles} of beer.\n".capitalize
    lyrics +=
      if n > 0
        "#{n.take_one_down} and pass it around, #{(n-1).bottles} of beer on the wall.\n"
      else
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
      end
    lyrics
  end

  def verses start, stop
    start.downto(stop).each_with_object('') { |which_verse,song| song << verse(which_verse) + "\n" }
  end

  def sing
    verses 99, 0
  end

end
