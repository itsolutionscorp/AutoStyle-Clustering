class Beer

  def verse(num)
    lyrics(num).chomp
  end

  def sing(start, stop = 0)
    song = ""
    (stop..start).each do |number|
      song = lyrics(number) + song
    end
    song
  end

  def lyrics(num)
    first_line(num) + second_line(num)
  end

  def first_line(num)
    "#{n_bottles(num).capitalize} of beer on the wall, #{n_bottles(num)} of beer.\n"
  end

  def second_line(num)
    if num == 1
      prep = "it"
      "Take #{prep} down and pass it around, #{n_bottles(num-1)} of beer on the wall.\n\n"
    elsif num == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n"
    else
      prep = "one"
      "Take #{prep} down and pass it around, #{n_bottles(num-1)} of beer on the wall.\n\n"
    end
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
