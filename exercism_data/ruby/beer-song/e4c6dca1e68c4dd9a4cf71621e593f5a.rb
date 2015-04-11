class Beer

  def verse(num)
    lyrics(num).chomp
  end

  def lyrics(num)
    first_line = "#{num} bottles of beer on the wall, #{num} bottles of beer.\n"
    second_line = "Take one down and pass it around, #{num-1} bottles of beer on the wall.\n\n"
    if num == 2
      first_line = "#{num} bottles of beer on the wall, #{num} bottles of beer.\n"
      second_line = "Take one down and pass it around, #{num-1} bottle of beer on the wall.\n\n"
    elsif num == 1
      first_line = "#{num} bottle of beer on the wall, #{num} bottle of beer.\n"
      second_line = "Take it down and pass it around, no more bottles of beer on the wall.\n\n"
    elsif num == 0
      first_line = "No more bottles of beer on the wall, no more bottles of beer.\n"
      second_line = "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n"
    end
    first_line + second_line
  end

  def sing(start, stop = 0)
    range = stop..start
    song = ""
    range.each do |number|
      song = lyrics(number) + song
    end
    song
  end

end
