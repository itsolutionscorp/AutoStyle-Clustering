class Beer
  SEPARATOR = "\n"

  def sing(start, finish=0)
    start.downto(finish).map(&method(:verse)).join(SEPARATOR) + SEPARATOR
  end

  def verse(number)
    Verse.new(number).to_s
  end

  class Verse
    def initialize(number)
      @this_bottle = bottles_for(number)
      @next_bottle = bottles_for(number - 1)
    end

    def to_s
      stanzas.join(SEPARATOR)
    end

    def stanzas
      [
        "#@this_bottle of beer on the wall, #@this_bottle of beer.".capitalize,
        "#{@this_bottle.take_down}, #@next_bottle of beer on the wall.",
        ""
      ]
    end

    private

    def bottles_for(number)
      if number < 0
        Bottles.new(99)
      elsif number == 0
        NoBottles.new
      elsif number == 1
        Bottle.new
      else
        Bottles.new(number)
      end
    end
  end

  class NoBottles
    def take_down
      "Go to the store and buy some more"
    end

    def to_s
      "no more bottles"
    end
  end

  class Bottle
    def to_s
      "1 bottle"
    end

    def take_down
      "Take it down and pass it around"
    end
  end

  class Bottles
    def initialize(number)
      @number = number
    end

    def take_down
      "Take one down and pass it around"
    end

    def to_s
      "#@number bottles"
    end
  end
end
