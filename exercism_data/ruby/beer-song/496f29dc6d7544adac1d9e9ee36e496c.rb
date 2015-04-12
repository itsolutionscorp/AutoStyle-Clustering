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

    def count(count = bottle_count)
      case count
      when -1 then 99.to_s
      when 0 then 'no more'
      else
        count
      end
    end

    def container(count = bottle_count)
      case count
      when 1 then 'bottle'
      else
        'bottles'
      end
    end

    def action
      case bottle_count
      when 0 then "Go to the store and buy some more"
      else
        "Take #{pronoun} down and pass it around"
      end
    end

    def pronoun
      case bottle_count
      when 1 then 'it'
      else
        'one'
      end
    end
  end
end