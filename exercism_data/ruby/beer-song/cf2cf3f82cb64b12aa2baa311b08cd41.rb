require 'pry'

class BeerSong
  def initialize
  end

  def verse(number)
    if number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{bottles(number)} of beer on the wall, #{bottles(number)} of beer.\nTake #{one_or_it(number)} down and pass it around, #{bottles(number - 1)} of beer on the wall.\n"
    end
  end

  def verses(start, finish)
    result = ''
    start.downto(finish).each { |number| result += verse(number) + "\n" }
    result
  end

  def sing
    verses(99,0)
  end

  def bottles(number)
    if number == 1
      "1 bottle"
    elsif number == 0
      "no more bottles"
    else
      "#{number} bottles"
    end
  end

  def one_or_it(number)
    number == 1 ? "it" : "one"
  end
end

binding.pry
