class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(upper_bound, lower_bound)
    upper_bound.downto(lower_bound).map {|n| verse(n)}.join("\n") << "\n"
  end

  def verse(bottle_count)
    Verse.new(bottle_count).to_s
  end

  class Verse
    attr_reader :bottle_count

    def initialize(bottle_count)
      @bottle_count = bottle_count
    end

    def to_s
      "#{count} #{container} of beer on the wall, ".capitalize +
      "#{count} #{container} of beer.\n" +
      "#{action}, " +
      "#{count(bottle_count - 1)} #{container(bottle_count - 1)} of beer on the wall.\n"
    end

    private

    def count(i = bottle_count)
      case i
      when -1
        99.to_s
      when 0
        'no more'
      else
        i.to_s
      end
    end

    def container(i = bottle_count)
      case i
      when 1
        'bottle'
      else
        'bottles'
      end
    end

    def action
      case bottle_count
      when 0
        "Go to the store and buy some more"
      else
        "Take #{pronoun} down and pass it around"
      end
    end

    def pronoun
      case bottle_count
      when 1
        'it'
      else
        'one'
      end
    end
  end
end
