class BeerSong

  def verse(number_of_beers)
    Verse.new(number_of_beers).to_s
  end

  def verses(first, last)
    first.downto(last).map do |bottles|
      verse(bottles) + "\n"
    end.join
  end

  def sing
    verses(Verse::MAX_BEERS, 0)
  end

  class Verse
    attr_reader :beers

    def initialize(beers)
      @beers = beers % (MAX_BEERS+1)
    end

    def to_s
      starting_beers + beers_left
    end

  protected

    def starting_beers
      wall.capitalize + ', ' + bottles_of_beer + ".\n"
    end

    def beers_left
      take_one + ', ' + next_wall + ".\n"
    end

    def wall
      bottles_of_beer + ' on the wall'
    end

    def take_one
      if beers.zero?
        'Go to the store and buy some more'
      else
        "Take #{next_beer} down and pass it around"
      end
    end

    def next_wall
      self.class.new(beers-1).wall
    end

    def next_beer
      beers == 1 ? 'it' : 'one'
    end

    def bottles_of_beer
      case beers
      when 0 then 'no more bottles of beer'
      when 1 then '1 bottle of beer'
      else "#{beers} bottles of beer"
      end
    end

    MAX_BEERS = 99
  end
end
