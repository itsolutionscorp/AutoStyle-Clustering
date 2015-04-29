class Beer
  class Crate
    def initialize(bottles)
      @bottles = bottles
    end

    def ==(other)
      @bottles == Integer(other)
    end

    def take_one_down
      @bottles == 0 ? self.class.new(99) : self.class.new(@bottles - 1)
    end

    def to_s
      case @bottles
        when 0 then "no more bottles"
        when 1 then "1 bottle"
        else        "#@bottles bottles"
      end
    end

    def to_i
      @bottles
    end
  end

  def verse(bottles)
    crate = Crate.new bottles

    line_one = "#{crate} of beer on the wall, #{crate} of beer.\n".capitalize

    if bottles == 0
      line_two = "Go to the store and buy some more, "
    else
      line_two = "Take #{bottles == 1 ? "it" : "one"} down and pass it around, "
    end

    return line_one + line_two + "#{crate.take_one_down} of beer on the wall.\n"
  end

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).map { |i| verse(i) + "\n" }.join
  end
end
