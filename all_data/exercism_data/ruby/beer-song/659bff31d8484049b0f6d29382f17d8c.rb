class Beer

  def verse(num)
    lyrics(num).chomp
  end

  def sing(start, stop = 0)
    range = stop..start
    song = ""
    range.each do |number|
      song = lyrics(number) + song
    end
    song
  end

  def lyrics(num)
    first_line = "#{n_bottles(num).capitalize} of beer on the wall, #{n_bottles(num)} of beer.\n"
    second_line = "Take one down and pass it around, #{n_bottles(num-1)} of beer on the wall.\n\n"
    if num == 1
      second_line = "Take it down and pass it around, #{n_bottles(num-1)} of beer on the wall.\n\n"
    elsif num == 0
      second_line = "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n"
    end
    first_line + second_line
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
