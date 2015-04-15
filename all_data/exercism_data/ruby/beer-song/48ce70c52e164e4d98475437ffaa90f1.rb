class Beer
  module Countable
    attr :count

    def initialize(count)
      @count = count
    end

    def singular?
      count == 1
    end
  end

  class Numeral
    include Countable

    def to_s
      present? ? count.to_s : 'no more'
    end

    def present?
      count > 0
    end
  end

  class Pronoun
    include Countable

    def to_s
      singular? ? 'it' : 'one'
    end
  end

  class QuantifiedBeer
    attr :numeral

    def initialize(count)
      @numeral = Numeral.new(count)
    end

    def to_s
      "#{numeral} #{quantifier} of beer"
    end

    def quantifier
      singular? ? 'bottle' : 'bottles'
    end

    private

    def singular?
      numeral.singular?
    end
  end

  Line = Struct.new(:count) do
    BREAK = "\n"

    def build
      raise NotImplementedError
    end

    def to_s
      build.capitalize.concat(BREAK)
    end

    private

    def beer
      QuantifiedBeer.new(count)
    end
  end

  class FirstLine < Line
    def build
      "#{beer} on the wall, #{beer}."
    end
  end

  class SecondLine < Line
    def build
      "#{take_one_or_buy_more}, #{beer} on the wall."
    end

    private

    def take_one_or_buy_more
      count > 0 ? take_one : buy_more
    end

    def take_one
      pronoun = Pronoun.new(count)
      self.count -= 1

      "Take #{pronoun} down and pass it around"
    end

    def buy_more
      self.count = 99
      'Go to the store and buy some more'
    end
  end


  class Verse
    RHYME_PATTERN = %i(a b)

    attr :count

    def initialize(count)
      @count = count
    end

    def to_s
      RHYME_PATTERN.map { |unit| self.send(unit) }.join
    end

    def a
      FirstLine.new(count)
    end

    def b
      SecondLine.new(count)
    end
  end

  class Song
    BREAK = "\n"

    attr :start, :finish

    def initialize(start, finish)
      @start, @finish = start, finish
    end

    def sing
      verses.zip([BREAK] * verses.count).join
    end

    def verses
      count_down { |count| Verse.new(count) }
    end

    private

    def count_down
      start.downto(finish).map { |count| yield count }
    end
  end

  def sing(start, finish = 0)
    Song.new(start, finish).sing
  end

  def verse(count)
    Verse.new(count).to_s
  end
end
