class Beer

  def verse(number)
    case number
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\n" +
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{bottles(number)} of beer on the wall, #{bottles(number)} of beer.\n" +
      "Take #{bottle(number)} down and pass it around, #{bottles(number - 1)} of beer on the wall.\n"
    end
  end

  def sing(start = 99, finish = 0)
    start.downto(finish).inject('') do |song, verse|
      song << verse(verse) + "\n"
    end
  end

private

  def bottles(number)
    case number
    when 0
      'no more bottles'
    when 1
      '1 bottle'
    else
      "#{number} bottles"
    end
  end

  def bottle(number)
    number == 1 ? 'it' : 'one'
  end

end
