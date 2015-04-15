class Beer
  def verse(num)
    join_lines(verse_lines(num))
  end

  def sing(first, last = 0)
    lines = (last..first).to_a.reverse.map do |num|
      verse_lines(num) + ['']
    end
    join_lines(lines)
  end

  private

  def verse_lines(bottles_of_beer)
    [
      "#{num(bottles_of_beer).capitalize} on the wall, " +
        "#{num(bottles_of_beer)}.",

      if bottles_of_beer.zero?
        "Go to the store and buy some more, " +
          "99 bottles of beer on the wall."
      else
        "Take #{one(bottles_of_beer)} down and pass it around, " +
          "#{num(bottles_of_beer - 1)} on the wall."
      end,
    ]
  end

  def num(num)
    case num
    when 0 then 'no more bottles of beer'
    when 1 then '1 bottle of beer'
    else "#{num} bottles of beer"
    end
  end

  def one(bottles_of_beer)
    bottles_of_beer == 1 ? 'it' : 'one'
  end

  def join_lines(lines)
    lines.join("\n") + "\n"
  end
end
