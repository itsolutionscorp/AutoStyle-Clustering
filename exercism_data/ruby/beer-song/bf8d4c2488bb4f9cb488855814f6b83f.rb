class BeerSong

  def verse(count)
    Verse.new(count).to_s
  end

  def verses(from, to)
    from.downto(to).inject("") do |song, count|
      "#{song}#{verse count}\n"
    end
  end

  def sing
    verses 99, 0
  end

private

  Verse = Struct.new(:count) do
    def to_s
      "#{first_part}#{second_part}"
    end

    def first_part
      "#{x_bottles} of beer on the wall, #{x_bottles} of beer.\n".capitalize
    end

    def second_part
      if count == 0
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
      else
        "Take #{one_bottle} down and pass it around, #{remaining_bottles} of beer on the wall.\n"
      end
    end

    def x_bottles
      pluralize count, "bottle"
    end

    def remaining_bottles
      pluralize (count - 1), "bottle"
    end

    def one_bottle
      count == 1 ? "it" : "one"
    end

    def pluralize(count, noun)
      "#{count == 0 ? "no more" : count} #{noun}" + ("s" if count != 1).to_s
    end
  end
end
