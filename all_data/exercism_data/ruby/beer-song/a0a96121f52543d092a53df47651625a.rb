class Beer

  def verse(number)
    "#{bottle_count(number).capitalize} on the wall, #{bottle_count(number)}.\n#{action(number)}, #{bottle_count(number - 1)} on the wall.\n"
  end

  def sing(start, finish = 0)
    verses = start.downto(finish).map do |bottle|
      verse(bottle)
    end
    "#{verses.join("\n")}\n"
  end

  private

    def action(bottle_count)
      case bottle_count
      when 0 then "Go to the store and buy some more"
      when 1 then "Take it down and pass it around"
      else "Take one down and pass it around"
      end
    end

    def bottle_count(bottle_count)
      bottle_count = 99 if bottle_count < 0
      case bottle_count
      when 0 then "no more bottles of beer"
      when 1 then "1 bottle of beer"
      else "#{bottle_count} bottles of beer"
      end
    end
end
