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
      what_to_remove = bottle_count > 1 ? "one" : "it"
      if bottle_count.zero?
        "Go to the store and buy some more, "
      else
        "Take #{what_to_remove} down and pass it around, "
      end
    end

    def bottle_count(bottle_count)
      word_for_bottles = "bottle#{'s' if bottle_count > 1}"
      if bottle_count.zero?
        "no more bottles of beer"
      else
        "#{bottle_count} #{word_for_bottles} of beer"
      end
    end
end
