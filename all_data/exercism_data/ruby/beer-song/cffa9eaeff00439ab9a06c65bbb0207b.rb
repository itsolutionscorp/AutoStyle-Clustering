class BeerSong
  def verse(num)
    case num
    when 0
      zero_verse
    when 1
      first_verse
    when 2
      basic_verse num, 'bottle'
    else
      basic_verse num
    end
  end

  def sing
    verses(99,0)
  end

  def verses(start, finish)
    start.downto(finish).reduce('') { |result, n| result += "#{verse(n)}\n" }
  end

  def basic_verse(num, container = 'bottles')
    "#{num} bottles of beer on the wall, #{num} bottles of beer.\n" \
    "Take one down and pass it around, #{num-1} #{container} of beer on the wall.\n"
  end

  def first_verse
    "1 bottle of beer on the wall, 1 bottle of beer.\n" \
    "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def zero_verse
    "No more bottles of beer on the wall, no more bottles of beer.\n" \
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
end
