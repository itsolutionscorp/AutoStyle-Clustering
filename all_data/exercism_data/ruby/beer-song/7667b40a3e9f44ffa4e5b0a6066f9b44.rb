class Beer
  def verse(number)
    BottleCollection.new(number).to_verse
  end

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).map { |number| verse(number) + "\n" }.join
  end
end

class Beer
  class BottleCollection
    MAX_SIZE = 99

    def self.full
      new(MAX_SIZE)
    end

    def initialize(count)
      @count = count
    end

    def to_verse
      "#{status}\n#{suggestion}\n"
    end

    def to_s
      case @count
      when 0 then "no more bottles of beer"
      when 1 then "1 bottle of beer"
      else        "#{@count} bottles of beer"
      end
    end

    private

    def status
      "#{to_s.capitalize} on the wall, #{to_s}."
    end

    def suggestion
      if @count.zero?
        "Go to the store and buy some more, #{self.class.full} on the wall."
      else
        "Take #{deictic} down and pass it around, #{collection_after_taking_one} on the wall."
      end
    end

    def collection_after_taking_one
      self.class.new(@count - 1)
    end

    def deictic
      @count == 1 ? "it" : "one"
    end
  end
end
