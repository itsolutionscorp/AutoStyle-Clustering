class Beer
  def sing(start, finish = 0)
    start.downto(finish).collect do |number|
      verse(number) + "\n"
    end.join
  end

  def verse(number)
    verse = if [0, 1, 2].include?(number)
      Kernel.const_get("Beer::Verse#{number}").new
    else
      Verse.new(number)
    end

    verse.to_s
  end

  class Verse
    def initialize(number)
      @number = number
    end

    def to_s
      "#{@number} bottles of beer on the wall, #{@number} bottles of beer.\nTake one down and pass it around, #{@number - 1} bottles of beer on the wall.\n"
    end
  end

  class Verse2
    def to_s
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    end
  end

  class Verse1
    def to_s
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    end
  end

  class Verse0
    def to_s
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end
end
