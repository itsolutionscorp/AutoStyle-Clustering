class Beer
  attr_reader :count

  def verse(num)
    @count = num
    lyrics = []

    #SING IT
    lyrics << bottles_of_beer(count).capitalize
    lyrics << space
    lyrics << on_the_wall
    lyrics << comma
    lyrics << space
    lyrics << bottles_of_beer(count)
    lyrics << period
    lyrics << new_line
    lyrics << drink_while_you_think
    lyrics << comma
    lyrics << space
    lyrics << bottles_of_beer(remaining)
    lyrics << space
    lyrics << on_the_wall
    lyrics << period
    lyrics << new_line

    lyrics.join
  end

  def sing(first, last=0)
    lyrics = []
    first.downto(last).each do |n|
      lyrics << verse(n)
      lyrics << new_line
    end
    lyrics.join
  end

  private

  def drink_while_you_think
    if count == 0
      "Go to the store and buy some more"
    elsif count == 1
      "Take it down and pass it around"
    else
      "Take one down and pass it around"
    end
  end

  def format(num)
    num == 0 ? "no more" : num
  end

  def remaining
    count > 0 ? count-1 : 99
  end

  def bottles_of_beer(num)
    num == 1 ? "1 bottle of beer" : "#{format(num)} bottles of beer"
  end

  def on_the_wall
    "on the wall"
  end

  def comma
    ","
  end

  def period
    "."
  end

  def space
    " "
  end

  def new_line
    "\n"
  end
end
