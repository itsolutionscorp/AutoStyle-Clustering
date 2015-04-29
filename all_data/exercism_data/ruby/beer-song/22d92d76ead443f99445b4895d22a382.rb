class Beer
  SEPARATOR = "\n"

  def sing(start, finish=0)
    start.downto(finish).map(&method(:verse)).join(SEPARATOR) + SEPARATOR
  end

  def verse(number)
    Verse.new(number).to_s
  end

  class Verse
    MAX = 99
    def initialize(number)
      @this_bottle = bottles_for(number)
      @next_bottle = bottles_for(number - 1)
    end

    def to_s
      lines.join(SEPARATOR)
    end

    def lines
      [
        "#@this_bottle of beer on the wall, #@this_bottle of beer.".capitalize,
        "#{@this_bottle.action}, #@next_bottle of beer on the wall.",
        ""
      ]
    end

    private

    def bottles_for(number)
      {
        -1 => Bottles.new(MAX),
        0  => NoBottles.new,
        1  => Bottle.new
      }.fetch(number, Bottles.new(number))
    end
  end

  class NoBottles
    def action
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

    def action
      "Take it down and pass it around"
    end
  end

  class Bottles
    def initialize(number)
      @number = number
    end

    def action
      "Take one down and pass it around"
    end

    def to_s
      "#@number bottles"
    end
  end
end
