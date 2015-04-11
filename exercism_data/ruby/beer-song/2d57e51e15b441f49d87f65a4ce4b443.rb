class BeerSong

  def verse num
    "#{quantity(num)} #{container(num)} of beer on the wall, "+
    "#{quantity(num).downcase} #{container(num)} of beer.\n" + 
    "#{action(num)}, #{quantity(num-1).downcase} #{container(num-1)} of beer on the wall.\n"
  end

  def verses upper_bound, lower_bound 
    upper_bound
      .downto(lower_bound)
      .collect { |verse_number| verse(verse_number) }
      .join("\n") << "\n"
  end

  def sing
    verses 99, 0
  end

  private

  def action num
    if num == 0
      "Go to the store and buy some more"
    else 
      "Take #{pronoun(num)} down and pass it around" 
    end
  end

  def container num
    num == 1 ? "bottle" : "bottles"
  end 

  def pronoun num
    num == 1 ? "it" : "one"
  end


  def quantity num
    if num == 0 
      "No more"
    else
      num == -1 ? "99" : "#{num}"
    end  
  end 

end
