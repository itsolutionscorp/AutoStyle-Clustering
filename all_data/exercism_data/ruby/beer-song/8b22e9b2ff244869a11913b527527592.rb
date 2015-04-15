# "8 bottles of beer on the wall, 8 bottles of beer.\nTake one down and pass it around, 7 bottles of beer on the wall.\n"

class BeerSong
  def verse(n)
    "#{show_number(n).capitalize} #{unit(n)} of beer on the wall, " <<
    "#{show_number(n)} #{unit(n)} of beer.\n" <<
    "#{first_action(n)} and #{second_action(n)}, " <<
    "#{show_number(n - 1)} #{unit(n - 1)} of beer on the wall.\n"
  end

  def verses(start, last)
    start.step(last, -1).reduce("") do |song, n|
      song << verse(n) << "\n"
    end
  end

  def sing
    verses(99, 0)
  end

  private

  def show_number(n)
    case n
    when 0
      "no more"
    when -1
      "99"
    else
      "#{n}"
    end
  end

  def unit(n)
    return "bottle" if n == 1
    "bottles"
  end

  def first_action(n)
    case n
    when 0
      "Go to the store"
    when 1
      "Take it down"
    else
      "Take one down"
    end
  end

  def second_action(n)
    return "buy some more" if n == 0
    "pass it around"
  end

end
