class BeerSong
  
  def verse(num)
    curent_beer(num) + remain_beer(num)
  end

  def verses(from, to)
    from.downto(to).inject('') { |song, num| song + "#{verse(num)}\n" }
  end

  def sing
    verses(99, 0)
  end

  private
  def curent_beer(num)
    return "No more bottles of beer on the wall, no more bottles of beer.\n" if num == 0
    return "#{num} bottle of beer on the wall, #{num} bottle of beer.\n" if num == 1
    "#{num} bottles of beer on the wall, #{num} bottles of beer.\n"
  end

  def remain_beer(num)
    return "Go to the store and buy some more, 99 bottles of beer on the wall.\n" if num == 0
    return "Take it down and pass it around, no more bottles of beer on the wall.\n" if num == 1
    return "Take one down and pass it around, #{num - 1} bottle of beer on the wall.\n" if num == 2
    "Take one down and pass it around, #{num - 1} bottles of beer on the wall.\n"
  end

end
