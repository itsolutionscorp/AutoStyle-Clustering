class BeerSong
  def verse(n)
    "#{show_number(n).capitalize} #{unit(n)} of beer on the wall, " <<
    "#{show_number(n)} #{unit(n)} of beer.\n" <<
    "#{first_action(n).capitalize} and #{second_action(n)}, " <<
    "#{show_number(n - 1)} #{unit(n - 1)} of beer on the wall.\n"
  end

  def verses(start, last = 0)
    start.step(last, -1).map{ |n| verse n }.push("").join("\n")
  end

  def sing
    verses 99
  end

  private

  def show_number(n)
    case n
    when  0 then "no more"
    when -1 then "99"
    else         "#{n}"
    end
  end

  def unit(n)
    n == 1 ? "bottle" : "bottles"
  end

  def first_action(n)
    case n
    when 0 then "go to the store"
    when 1 then "take it down"
    else        "take one down"
    end
  end

  def second_action(n)
    n == 0 ? "buy some more" : "pass it around"
  end
end
