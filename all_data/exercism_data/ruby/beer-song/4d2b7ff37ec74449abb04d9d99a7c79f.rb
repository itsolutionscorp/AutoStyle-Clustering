class BeerSong
  MAX = 99

  class BottlesFactory
    def self.make(nb)
      if nb > 1
        ManyBottles.new(nb)
      elsif nb == 1
        OneBottle.new
      elsif nb == 0
        ZeroBottle.new
      else
        ManyBottles.new(MAX)
      end
    end
  end

  class BottlesAmount
    def -(amount)
      BottlesFactory.make(nb-amount)
    end

    def to_s
      "bottle"
    end
  end

  class OneOrMoreBottles < BottlesAmount
    def drink(this)
      "Take #{this} down and pass it around"
    end
  end

  class ManyBottles < OneOrMoreBottles
    attr_reader :nb

    def initialize(nb)
      @nb = nb
    end

    def to_s
      "#{@nb} #{super}s"
    end

    def drink
      super("one")
    end
  end

  class OneBottle < OneOrMoreBottles
    def nb; 1; end

    def to_s
      "1 #{super}"
    end

    def drink
      super("it")
    end
  end

  class ZeroBottle < BottlesAmount
    def nb; 0; end

    def to_s
      "no more #{super}s"
    end

    def drink
      "Go to the store and buy some more"
    end
  end

  def verse(bottles_nb)
    bottles = BottlesFactory.make(bottles_nb)
    str = "#{bottles} of beer on the wall, #{bottles} of beer.\n".capitalize \
          + "#{bottles.drink}, #{bottles-1} of beer on the wall.\n"
  end

  def verses(first, last)
    first.downto(last).each_with_object('') do |bottles_nb, str|
      str << verse(bottles_nb) + "\n"
    end
  end

  def sing
    verses(MAX, 0)
  end
end
