class Beer
  def verse(bottle)
    Verses.select(bottle).text
  end

  def sing(from, to = 0)
    Song.new(from, to).text
  end

  class Verses
    def self.select(bottle)
      case bottle
      when 0
        ZeroBottleVerse.new(bottle)
      when 1
        OneBottleVerse.new(bottle)
      when 2
        TwoBottleVerse.new(bottle)
      else
        ManyBottleVerse.new(bottle)
      end
    end
  end

  class Song
    attr_reader :from, :to
    private :from, :to

    def initialize(from, to)
      @from, @to = from, to
    end

    def text
      range.map { |bottle| Verses.select(bottle).text }.join("\n") << "\n"
    end

    private

    def range
      (to..from).to_a.reverse
    end
  end

  class Verse
    attr_reader :bottle
    private :bottle

    def initialize(bottle)
      @bottle = bottle
    end

    def text
      lines.join("\n") << "\n"
    end

    private

    def next_bottle
      bottle - 1
    end
  end

  class ManyBottleVerse < Verse
    def lines
      [
        "#{bottle} bottles of beer on the wall, #{bottle} bottles of beer.",
        "Take one down and pass it around, #{next_bottle} bottles of beer on the wall."
      ]
    end
  end

  class ZeroBottleVerse < Verse
    def lines
      [
        "No more bottles of beer on the wall, no more bottles of beer.",
        "Go to the store and buy some more, 99 bottles of beer on the wall."
      ]
    end
  end

  class OneBottleVerse < Verse
    def lines
      [
        "1 bottle of beer on the wall, 1 bottle of beer.",
        "Take it down and pass it around, no more bottles of beer on the wall."
      ]
    end
  end

  class TwoBottleVerse < Verse
    def lines
      [
        "2 bottles of beer on the wall, 2 bottles of beer.",
        "Take one down and pass it around, 1 bottle of beer on the wall."
      ]
    end
  end
end
