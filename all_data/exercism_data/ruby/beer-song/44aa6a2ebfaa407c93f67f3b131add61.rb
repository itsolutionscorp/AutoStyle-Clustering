class BeerSong
  def verse n
    "#{(bottles n).capitalize} on the wall, #{bottles n}.\n" \
      "#{thing_to_do n}, #{bottles (n - 1)} on the wall.\n"
  end

  def verses first, last
    first.downto(last).map { |n| verse n }.join("\n") + "\n"
  end
  
  def sing
    verses 99, 0
  end

  private
    def bottles n
      n %= 100
      case n
        when 0 then 'no more bottles of beer'
        when 1 then '1 bottle of beer'
        else "#{n} bottles of beer"
      end
    end

    def thing_to_do n
      if n != 0 then "Take #{n == 1 ? 'it' : 'one'} down and pass it around"
      else 'Go to the store and buy some more'
      end
    end
end
