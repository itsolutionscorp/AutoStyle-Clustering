class Beer

  def verse(number)
    lyrics(number).chomp
  end

  def sing(start, stop = 0)
    song = ""
    (stop..start).each do |number|
      song = lyrics(number) + song
    end
    song
  end

  def lyrics(number)
    first_line(number) + second_line(number)
  end

  def first_line(number)
    "#{n_bottles(number).capitalize} of beer on the wall, #{n_bottles(number)} of beer.\n"
  end

  def second_line(number)
    if number == 1
      line(number, "it")
    elsif number == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n"
    else
      line(number, "one")
   end
  end

  def line(number, prep)
    "Take #{prep} down and pass it around, #{n_bottles(number-1)} of beer on the wall.\n\n"
  end

  def n_bottles(number)
    if number == 0
      "no more bottles"
    elsif number == 1
      number.to_s + " bottle"
    else
      number.to_s + " bottles"
    end
  end

end
