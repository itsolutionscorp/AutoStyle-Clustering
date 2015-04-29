require 'pry'
class BeerSong

  def verse(num)
    return "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n" if num == 0

    bottle = num > 1 ? 'bottles' : 'bottle'
    less_bottle = num - 1 > 1 ? 'bottles' : 'bottle'

    verse = "#{num} #{bottle} of beer on the wall, #{num} #{bottle} of beer.\n"

    if num - 1 > 0
      verse << "Take one down and pass it around, #{num - 1} #{less_bottle} of beer on the wall.\n"
    else
      verse << "Take it down and pass it around, no more bottles of beer on the wall.\n"
    end
    verse
  end

  def verses(start, stop)
    # binding.pry
    start.downto(stop).inject('') { |a, e| a << verse(e) << "\n"}
  end

  def sing
    verses(99, 0)
  end
end
