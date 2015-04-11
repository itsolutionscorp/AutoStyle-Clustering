class BeerSong
  def verse num
    return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n" if num == 0
    return "#{num} bottle of beer on the wall, #{num} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n" if num == 1
    return "#{num} bottles of beer on the wall, #{num} bottles of beer.\nTake one down and pass it around, #{num-1} bottle of beer on the wall.\n" if num == 2
    return "#{num} bottles of beer on the wall, #{num} bottles of beer.\nTake one down and pass it around, #{num-1} bottles of beer on the wall.\n"
  end
  
  def verses from, to
    return (to..from).to_a.reverse.inject('') do |ret,num|
      "#{ret}#{verse(num)}\n"
    end
  end
  
  def sing
    verses(99, 0)
  end
end
