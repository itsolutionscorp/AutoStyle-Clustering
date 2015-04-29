class Beer

  def verse number
    case number
      when 0 then last_verse
      when 1 then verse1
      when 2 then verse2
      else verses(number)
    end
  end

  def sing(start, finish = 0)
    start.downto(finish).inject("") { |song, v| song << "#{verse(v)}\n" }
  end

  private

  def verse1
    "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def verse2
   "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
  end

  def verses number
    "8 bottles of beer on the wall, 8 bottles of beer.\nTake one down and pass it around, 7 bottles of beer on the wall.\n"
   "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number -1} bottles of beer on the wall.\n"
  end

  def last_verse
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
end
