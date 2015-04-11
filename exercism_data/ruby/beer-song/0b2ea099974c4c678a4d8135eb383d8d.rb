class BeerSong

  def verse(verse_number)
    text = "#{bottles_of_beer(verse_number).capitalize}, #{bottles_of_beer(verse_number, with_wall=false)}.\n"
    if verse_number > 0
      text += "Take #{to_down(verse_number)} down and pass it around, #{bottles_of_beer(verse_number - 1)}.\n"
    else
      text += "Go to the store and buy some more, #{bottles_of_beer(99)}.\n"
    end
  end

  def verses(upper, lower)
    (lower..upper).to_a.reverse.each_with_object("") do |verse_number, text|
      text.concat "#{verse(verse_number)}\n"
    end
  end

  def sing
    verses(99, 0)
  end

  private

  def bottles_of_beer(count, with_wall=true)
    "#{beer_count(count)} #{pluralize_bottle(count)} of beer" + (with_wall ? " on the wall" : "")
  end

  def pluralize_bottle(count)
    count == 1 ? "bottle" : "bottles"
  end

  def beer_count(count)
    count == 0 ? "no more" : count
  end

  def to_down(count)
    count > 1 ? "one" : "it"
  end

end
