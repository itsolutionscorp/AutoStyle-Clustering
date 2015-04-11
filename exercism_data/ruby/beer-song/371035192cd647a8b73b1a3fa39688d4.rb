class BeerSong
  STARTING_BOTTLE_COUNT = 99

  def verse(n)
    first_line(n) + second_line(n)
  end

  def verses(start, stop)
    start.downto(stop).map { |verse|
      verse(verse)
    }.join("\n") + "\n"
  end

  def sing
    verses(STARTING_BOTTLE_COUNT, 0)
  end

  private

  def first_line(n)
    bottle_count = bottle_count(n)
    bottles = pluralize_bottles(n)
    "#{capitalize(bottle_count)} #{bottles} of beer on the wall," \
    " #{bottle_count} #{bottles} of beer.\n"
  end

  def second_line(n)
    if n == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "Take #{article(n)} down and pass it around, " \
      "#{bottle_count(n - 1)} #{pluralize_bottles(n - 1)} of beer on the wall.\n"
    end
  end

  def bottle_count(n)
    if n == 0
      "no more"
    else
      n.to_s
    end
  end

  def pluralize_bottles(number)
    ending = number == 1 ? "" : "s"
    "bottle" + ending
  end

  def capitalize(word)
    word[0].upcase + word[1..-1]
  end

  def article(n)
    n == 1 ? "it" : "one"
  end
end
