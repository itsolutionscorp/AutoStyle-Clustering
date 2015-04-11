class BeerSong
  def initialize
  end

  def verse(number)
    x = number
    number - 1 == 0 ? y = 'no more' : y = number - 1
    string = "#{x} bottles of beer on the wall, #{x} bottles of beer.\n"

    if number == 1
      string.gsub!(/bottles/, 'bottle')
      string << "Take it down and pass it around, no more bottles of beer on the wall.\n"
    elsif number == 0
      string = "No more bottles of beer on the wall, no more bottles of beer.\n"
      string << "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      second = "Take one down and pass it around, #{y} bottles of beer on the wall.\n"
      number == 2 ? string << second.gsub(/bottles/, 'bottle') : string << second
    end
    string
  end

  def verses(start, finish)
    array = start.downto(finish).map { |x| x }
    array.map! { |x| verse(x) }
    string = array.join("\n")
    string << "\n"
  end

  def sing
    verses(99, 0)
  end
end
