class Beer
  Line = Struct.new(:count) do
    BREAK = "\n"

    def text
      raise NotImplementedError
    end

    def to_s
      text.capitalize.concat(BREAK)
    end

    private

    def bottles
      if count > 1
        "#{count} bottles of beer"
      elsif count == 1
        '1 bottle of beer'
      else
        'no more bottles of beer'
      end
    end
  end

  class A < Line
    def text
      "#{bottles} on the wall, #{bottles}."
    end
  end

  class B < Line
    def text
      "#{take_one_or_buy_more}, #{bottles} on the wall."
    end

    private

    def take_one_or_buy_more
      count > 0 ? take_one : buy_more
    end

    def take_one
      self.count -= 1
      "Take #{count == 0 ? 'it' : 'one'} down and pass it around"
    end

    def buy_more
      self.count = 99
      'Go to the store and buy some more'
    end
  end


  Verse = Struct.new(:count) do
    def a
      A.new(count)
    end

    def b
      B.new(count)
    end

    def to_s
      [a, b].join
    end
  end

  Song = Struct.new(:start, :finish) do
    BREAK = "\n"

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
