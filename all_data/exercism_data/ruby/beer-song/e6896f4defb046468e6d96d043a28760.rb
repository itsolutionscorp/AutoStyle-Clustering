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
    initial_beers(number) + remaining_beers(number)
  end

  def initial_beers(number)
    "#{n_bottles(number).capitalize} of beer on the wall, #{n_bottles(number)} of beer.\n"
  end

  def remaining_beers(number)
    if number == 1
      last_beer(number, "it")
    elsif number == 0
      out_of_beer
    else
      plenty_of_beer(number, "one")
   end
  end

  def plenty_of_beer(number, pluralism)
    interpolate_second_line(number, pluralism)
  end

  def last_beer(number, pluralism)
    interpolate_second_line(number, pluralism)
  end

  def out_of_beer
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n"
  end

  def interpolate_second_line(number, pluralism)
    "Take #{pluralism} down and pass it around, #{n_bottles(number-1)} of beer on the wall.\n\n"
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
