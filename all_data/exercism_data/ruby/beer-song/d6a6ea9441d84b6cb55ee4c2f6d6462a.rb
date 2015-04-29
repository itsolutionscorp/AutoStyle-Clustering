class BeerSong

  def verse number
    verse_for(number).lyrics
  end

  def verses first, last
    verses = first.downto(last).map { |number| verse number }

    [ *verses, '' ].join "\n"
  end

  def sing
    verses 99, 0
  end

private

  def verse_for number
    factory = VerseFactory.new number
    factory.verse
  end

end


class BeerSong
  module Verses

    class Standard
      attr_reader :number

      def initialize number
        @number = number
      end

      def lyrics
        "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number.pred} bottles of beer on the wall.\n"
      end
    end

    class TwoBottles
      def lyrics
        "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
      end
    end

    class OneBottle
      def lyrics
        "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
      end
    end

    class NoBottles
      def lyrics
        "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
      end
    end

  end
end


class BeerSong
  class VerseFactory

    attr_reader :number

    def initialize number
      @number = number
    end

    def verse
      special or standard
    end

  private

    SpecialCases = {
      0 => Verses::NoBottles,
      1 => Verses::OneBottle,
      2 => Verses::TwoBottles
    }

    def special
      SpecialCases[number].new if SpecialCases.has_key? number
    end

    def standard
      Verses::Standard.new number
    end

  end
end
