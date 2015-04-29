require 'pry'

class BeerSong

  OF_BEER = " of beer"
  ON_THE_WALL = " on the wall"
  PASS_AROUND = "and pass it around, "
  BUY_MORE = "Go to the store and buy some more, "
  NEW_LINE = "\n"

  def verses(hi, lo)
    (lo..hi).to_a.reverse.each_with_object(""){|num, song|
      song << single_verse(num) << NEW_LINE
    }
  end

  def verse(num)
    single_verse(num)
  end

  def sing
    verses(99,0)
  end

  private

  def single_verse(num)
    first_sentence(num) + second_sentence(num)
  end

  def first_sentence(num)
    bottles(num).capitalize + ON_THE_WALL + ", " + bottles(num) + "." + NEW_LINE  
  end

  def second_sentence(num)
    take_or_buy(num) + bottles(num-1) + ON_THE_WALL + "." + NEW_LINE
  end

  def bottles(num)
    plural = {false => "", true => "s"}  
    number_of_bottles(num) + "bottle" + plural[num != 1] + OF_BEER
  end

  def number_of_bottles(num)
    num_of_bots = [
      "99 ",
      "no more ",
      "1 ",
      "#{num} "
      ]
    num_of_bots[[num+1,3].min]
  end

  def take_or_buy(num)
    it_one = ["it", "one"]
    num.zero? ? BUY_MORE : take_down(num)
  end

  def take_down(num)
    it_one = ["it", "one"]
    "Take #{it_one[[num-1,1].min]} down " + PASS_AROUND
  end
end

=begin
3 bottles of beer on the wall, 3 bottles of beer.
Take one down and pass it around, 2 bottles of beer on the wall.

2 bottles of beer on the wall, 2 bottles of beer.
Take one down and pass it around, 1 bottle of beer on the wall.

1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.

No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
=end
