class Beer
  def initialize(total = 99)
    @total = total
  end

  def verse(number)
    case number
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, #{bottles @total} of beer on the wall.\n"
    when 1
      "#{bottles number} of beer on the wall, #{bottles number} of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      left = number - 1
      "#{bottles number} of beer on the wall, #{bottles number} of beer.\nTake one down and pass it around, #{bottles left} of beer on the wall.\n"
    end
  end

  def sing(from, to=0)
    from.downto(to).map do |number|
      verse(number)
    end.join("\n") + "\n"
  end

  def self.song
    Beer.new.sing(@total)
  end

  private

  def bottles(number)
    number == 1 ? "1 bottle" : "#{number} bottles"
  end
end
