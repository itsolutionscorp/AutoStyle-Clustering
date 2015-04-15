class BeerSong

  def verse(count)
    Verse.new(count).sing
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
    def sing
      first_part = "#{bottles_on_wall}, #{bottles}.\n".capitalize
      "#{first_part}#{do_something}, #{bottles_on_wall}.\n"
    end

    def bottles_on_wall
      "#{bottles} on the wall"
    end

    def bottles
      "#{x_bottles} of beer"
    end

    def do_something
      if go_to_store?
        self.count = 99
        "Go to the store and buy some more"
      else
        self.count -= 1
        "Take #{one_bottle} down and pass it around"
      end
    end

    def go_to_store?
      count == 0
    end

    def x_bottles
      "#{count == 0 ? "no more" : count} bottle" + ("s" if count != 1).to_s
    end

    def one_bottle
      count == 0 ? "it" : "one"
    end
  end
end
