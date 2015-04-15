class Beer

  def sing(start, final = 0)
    song = ""
    start.downto(final) {|number| song << verse(number) + "\n"}
    song
  end

  def verse(qty)
    construct_verse(qty)
  end

  private
  def construct_verse(qty)
    wall = " on the wall"
    down = "down and pass it around,"
    starter = ( zero?(qty) + beers?(qty) + wall + ", " + zero?(qty) + beers?(qty) ).capitalize
    middle = ".\nTake " + last_one?(qty) + down + " " + zero?(qty-1)
    ender = beers?(qty-1) + wall + ".\n"
    
    starter + ( last_middle?(qty) || middle ) + ender
  end

  def zero?(qty)
    qty == 0 ? "no more" : qty.to_s
  end

  def beers?(qty)
    qty == 1 ? " bottle of beer" : " bottles of beer"
  end

  def last_one?(qty)
    qty == 1 ? "it " : "one "
  end

  def last_middle?(qty)
    ".\nGo to the store and buy some more, 99" if qty == 0
  end

end
