# == BeerSong
#
#    BeerSong's single responsibility is to arrage song verses.
class BeerSong
  def verse(bottle_number)
    current_bottle = BottleNumber.new(bottle_number)
    next_bottle = BottleNumber.new(current_bottle.next_number)

    "#{current_bottle.to_s} of beer on the wall, #{current_bottle.to_s} of beer.\n".capitalize +
    "#{current_bottle.command}, #{next_bottle.to_s} of beer on the wall.\n"
  end

  def verses(upper, lower)
    upper.downto(lower).map { |n| verse(n) }.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end
end

# == BottleNumber
#
#    BottleNumber's single responsibility is to construct a song verse.
class BottleNumber
  attr_reader :number
  private :number

  def initialize(number)
    @number = number
  end

  def next_number
    if number == 0
      99
    else
      number - 1
    end
  end

  def to_s
    "#{quantity} #{singular_or_plural}"
  end

  def command
    if number == 0
      "Go to the store and buy some more"
    else
      "Take #{pronoun} down and pass it around"
    end
  end

  private

  def quantity
    if number == 0
      "no more"
    else
      number
    end
  end

  def pronoun
    if number == 1
      "it"
    else
      "one"
    end
  end

  def singular_or_plural
    if number == 1
      "bottle"
    else
      "bottles"
    end
  end


end
