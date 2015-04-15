class BeerSong
  def initialize

  end

  def verse(num)
    if num > 1
      return "#{num} bottles of beer on the wall, #{num} bottles of beer.\nTake one down, pass it around #{num-1} bottles of beer on the wall.\n"
    elsif num == 1
      return "#{num} bottle of beer on the wall, #{num} bottle of beer.\nTake one down, pass it around no more bottles of beer on the wall.\n"
    end
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
end
