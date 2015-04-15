class BeerSong
  def initialize
  end

  def verse(num)
    string = "#{num} bottles of beer on the wall, #{num} bottles of beer.\n"
    edited(string, num)
    string
  end

  def verses(start, finish)
    start.downto(finish).map { |elem| verse(elem) }.join("\n") << "\n"
  end

  def sing
    verses(99, 0)
  end

  private

  def edited(string, num)
    if num > 1
      beers_on_the_wall(string, num)
    elsif num == 1 
      no_more(string)
    else 
      go_to_the_store(string)
    end
  end

  def no_more(string)
      string.gsub!(/bottles/, 'bottle')
      string << "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def go_to_the_store(string)
      string.clear << "No more bottles of beer on the wall, no more bottles of beer.\n"
      string << "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def beers_on_the_wall(string, num)
      second = "Take one down and pass it around, #{num-1} bottles of beer on the wall.\n"
      num == 2 ? string << second.gsub(/bottles/, 'bottle') : string << second
  end
end
