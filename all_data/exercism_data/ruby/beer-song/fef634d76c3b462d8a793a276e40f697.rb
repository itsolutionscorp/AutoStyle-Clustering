class Beer

  def verse(number)
    phrase = ""
    phrase << "#{bottle_count(number).capitalize} on the wall, "
    phrase << "#{bottle_count(number)}.\n"
    phrase << action(number)
    phrase << next_bottle(number)
  end

  def sing(start, finish = 0)
    start.downto(finish).reduce("") do |song, bottle|
      song << verse(bottle)
      song << "\n"
    end
  end

  private

    def next_bottle(number)
      "#{bottle_count(next_verse(number))} on the wall.\n"
    end

    def next_verse(verse_number)
      verse_number.zero? ? 99 : verse_number - 1
    end

    def action(bottle_count)
      case bottle_count
      when 0 then "Go to the store and buy some more, "
      when 1 then "Take it down and pass it around, "
      else "Take one down and pass it around, "
      end
    end

    def bottle_count(bottle_count)
      case bottle_count
      when 0 then "no more bottles of beer"
      when 1 then "1 bottle of beer"
      else "#{bottle_count} bottles of beer"
      end
    end
end
