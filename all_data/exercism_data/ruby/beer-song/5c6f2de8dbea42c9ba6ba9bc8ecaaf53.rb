class Beer
  attr_reader :count

  def initialize
  end

  def verse(count)
    @count = count
    return last_verse if count == 0
    "#{count} #{bottles} of beer on the wall, #{count} #{bottles} of beer.\nTake #{one_or_it} down and pass it around, #{last_beer} of beer on the wall.\n"
  end

  def sing(x, y=0)
    answer = []
    (y..x).each do |num|
      answer << "\n"
      answer << verse(num)
    end
    answer = answer.reverse
    answer.join("")
  end

  def last_verse
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def one_or_it
    return "it" if count == 1
    "one"
  end

  def bottles
    return "bottle" if count < 2
    "bottles"
  end

  def last_beer
    return "no more bottles" if count < 2
    return "1 bottle" if count == 2
    "#{count - 1} bottles"
  end

end
