class Beer
  def sing(start, finish = 0)
    start.downto(finish).collect do |number|
      verse(number) + "\n"
    end.join
  end

  def verse(number)
    Kernel.const_get("Beer::Verse#{number}")
  end

  99.downto(3) do |number|
    Kernel.const_set("Verse#{number}",
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number - 1} bottles of beer on the wall.\n"
    )
  end

  Verse2 = "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"

  Verse1 = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"

  Verse0 = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
end
