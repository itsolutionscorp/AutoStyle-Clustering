class BeerSong
  attr_reader :verse_number

  def verse number
    VerseFactory.new( number ).verse.sing
  end

  def verses start_verse, end_verse
    start_verse.downto( end_verse ).map { |number| verse( number )+ ("\n") }.join
  end

  def sing
    verses 99, 0
  end

  class VerseFactory
    attr_accessor :number

    def initialize number
      @number = number
    end

    def verse
      klass = case number
        when 0 then NoBottleVerse
        when 1 then LastBottleVerse
        else        ManyBottlesVerse
      end

      klass.new( number )
    end
  end

  class BaseVerse
    attr_accessor :bottles

    def initialize number
      @bottles = number
    end

    def sing
      prelude + action
    end

  private
    def prelude
      "#{bottles_ammount.capitalize} of beer on the wall, #{bottles_ammount} of beer.\n"
    end
  end

  class NoBottleVerse < BaseVerse
  private
    def action
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end

    def bottles_ammount
      "no more bottles"
    end
  end

  class LastBottleVerse < BaseVerse
  private
    def action
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    end

    def bottles_ammount
      "#{bottles} bottle"
    end
  end

  class ManyBottlesVerse < BaseVerse
  private
    def action
      "Take one down and pass it around, #{bottles_ammount( bottles-1 )} of beer on the wall.\n"
    end

    def bottles_ammount ammount = bottles
      "#{ammount} bottle#{'s' if ammount > 1}"
    end
  end
end
        
