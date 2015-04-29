class BeerSong
  def initialize

  end

  def verse number
    case number
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    when 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "#{number.to_s} bottles of beer on the wall, #{number.to_s} bottles of beer.\nTake one down and pass it around, #{(number-1).to_s} " + "#{number == 2 ? "bottle" : "bottles"} of beer on the wall.\n"
    end
  end

  def bottles_on_wall number
    if number < 1
      "No more bottles"
    elsif number == 1
      "1 bottle"
    else
      number.to_s + " bottles"
    end
  end

  def verses start_num, end_num
    ((end_num..start_num).entries.map{ |number| verse number}).reverse.join("\n") + "\n"
  end

  def sing
    verses 99, 0
  end
end
