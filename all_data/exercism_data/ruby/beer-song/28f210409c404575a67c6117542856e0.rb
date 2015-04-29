class Beer

  def verse(number)
    if number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif number == 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif number == 1
       "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number - 1} bottles of beer on the wall.\n"
    end
  end

  def sing(starting_number, ending_number = 0)
    stanzas = (ending_number..starting_number).to_a.reverse

    sing_verses(stanzas)
  end

  private

  def sing_verses(stanzas)
    stanzas.collect { |n| verse(n) }.join("\n") + "\n"
  end
end
