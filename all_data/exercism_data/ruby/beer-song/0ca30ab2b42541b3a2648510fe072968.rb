class Beer

  def verse(num)
    Verse.new(Wall.new(num)).to_s
  end

  def sing(start_at, end_at=0)
    verses(start_at, end_at).join("\n") << "\n"
  end

  def verses(start_at, end_at)
    start_at.downto(end_at).map { |num| verse(num) }
  end

  class Wall
    attr_accessor :bottles

    def initialize(bottles)
      @bottles = bottles
    end

    def take
      self.bottles -= 1
    end

    def buy_more
      self.bottles = 99
    end

    def bottles?
      bottles >= 1
    end

  end

  class Verse
    attr_reader :wall

    def initialize(wall)
      @wall = wall
    end

    def to_s
      [line1, line2].map(&:capitalize).join("\n") << "\n"
    end

    def line1
      "#{bottles_on_wall}, #{bottles_of_beer}."
    end

    def line2
      if wall.bottles?
        wall.take
        take_and_pass
      else
        wall.buy_more
        buy_some_more
      end
    end

    def take_and_pass
      "#{take_one} and pass it around, #{bottles_on_wall}."
    end

    def take_one
      if(wall.bottles?)
        'Take one down'
      else
        'Take it down'
      end
    end

    def buy_some_more
      "Go to the store and buy some more, #{bottles_on_wall}."
    end

    def bottles_on_wall
      "#{bottles_of_beer} on the wall"
    end

    def bottles_of_beer
      "#{bottles} of beer"
    end

    def bottles
      if(wall.bottles == 0)
        "no more bottles"
      elsif(wall.bottles == 1)
        "#{wall.bottles} bottle"
      else
        "#{wall.bottles} bottles"
      end
    end

  end
end
