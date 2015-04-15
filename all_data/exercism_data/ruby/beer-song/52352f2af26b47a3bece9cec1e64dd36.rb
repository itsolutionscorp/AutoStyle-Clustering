class BeerSong
  def verse(n)
    case n
    when 2
      two_bottles_of_beer
    when 1
      one_bottle_of_beer
    when 0
      no_bottles_of_beer
    else
      n_bottles_of_beer(n)
    end
  end

  def verses(first, last)
    first.downto(last).map { |n| verse(n) }.join("\n").concat("\n")
  end

  def sing
    verses(99, 0)
  end

  private

  def n_bottles_of_beer(n)
    "#{n} bottles of beer on the wall, #{n} bottles of beer.\n"\
    "Take one down and pass it around, #{n-1} bottles of beer on the wall.\n"
  end

  def two_bottles_of_beer
    "2 bottles of beer on the wall, 2 bottles of beer.\n"\
    "Take one down and pass it around, 1 bottle of beer on the wall.\n"
  end

  def one_bottle_of_beer
    "1 bottle of beer on the wall, 1 bottle of beer.\n"\
    "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def no_bottles_of_beer
    "No more bottles of beer on the wall, no more bottles of beer.\n"\
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
end
