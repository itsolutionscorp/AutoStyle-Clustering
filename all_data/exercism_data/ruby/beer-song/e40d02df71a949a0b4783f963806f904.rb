class Beer
  def verse(number)
    Verse.new(number).to_s
  end

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).map { |number| verse(number) + "\n" }.join
  end
end

class Beer
  class Verse
    def initialize(number)
      @number = number
    end

    def to_s
      "#{status}\n#{suggestion}\n"
    end

    private

    def status
      "#{bottles.to_s.capitalize} on the wall, #{bottles}."
    end

    def suggestion
      if bottles.none?
        "Go to the store and buy some more, #{Bottles.maximum} on the wall."
      else
        "Take #{bottles.deictic} down and pass it around, #{bottles.bottles_after_taking_one} on the wall."
      end
    end

    def bottles
      @bottles ||= Bottles.new(@number)
    end
  end
end

class Beer
  class Bottles
    MAX_SIZE = 99

    def self.maximum
      new(MAX_SIZE)
    end

    def initialize(count)
      @count = count
    end

    def to_s
      case @count
      when 0 then "no more bottles of beer"
      when 1 then "1 bottle of beer"
      else        "#{@count} bottles of beer"
      end
    end

    def none?
      @count == 0
    end

    def deictic
      @count == 1 ? "it" : "one"
    end

    def bottles_after_taking_one
      self.class.new(@count - 1)
    end
  end
end
