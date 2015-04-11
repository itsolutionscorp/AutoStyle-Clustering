class Beer
  class Formatter
    def self.pluralize(amount, singular, plural)
      if amount == 1
        singular
      else
        plural
      end
    end

    def self.with_separators(lines)
      lines.join("\n") + "\n"
    end
  end

  class Song
    attr_reader :from, :to

    def initialize(from, to)
      @from = from
      @to = to
    end

    def self.verse(number)
      return nil if number < 0
      (number.zero? ? LastVerse : Verse).new(number)
    end

    def verses
      from.downto(to).collect { |number|
        self.class.verse(number)
      }
    end

    def to_s
      Formatter.with_separators(verses)
    end
  end

  class Verse
    attr_reader :number_of_bottles

    def bottles
      "#{number_of_bottles} #{Formatter.pluralize(number_of_bottles, "bottle", "bottles")} of beer"
    end

    def initialize(number)
      @number_of_bottles = number
      @next_verse = Song.verse(number - 1)
    end

    def join(separator)
      lines.join(separator)
    end

    def to_s
      Formatter.with_separators(self)
    end

    private

    def first_line
      "#{bottles} on the wall, #{bottles.downcase}."
    end

    def lines
      [first_line, second_line]
    end

    def next_verse
      @next_verse
    end

    def second_line
      subject = Formatter.pluralize(number_of_bottles, "it", "one")
      "Take #{subject} down and pass it around, #{next_verse.bottles.downcase} on the wall."
    end
  end

  class LastVerse < Verse
    def bottles
      "No more bottles of beer"
    end

    def second_line
      "Go to the store and buy some more, 99 bottles of beer on the wall."
    end
  end

  def recite(from = 99, to = 0)
    `say '#{sing(from, to)}'`
  end

  def sing(from = 99, to = 0)
    Song.new(from, to).to_s
  end

  def verse(number)
    Song.verse(number).to_s
  end
end
