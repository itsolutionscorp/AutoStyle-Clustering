class Beer
  attr_reader :current_bottle
  def verse(num_bottles)
    @current_bottle = Bottle.new(num_bottles)

    lines = []
    lines << on_the_wall
    lines << pass_it_around
    lines.join("\n") + "\n"
  end

  def sing(starting, ending = 0)
    starting.downto(ending).each_with_object([]) do |num, memo|
      memo << verse(num)
    end.join("\n") + "\n"
  end

  def on_the_wall
    "#{current_bottle} on the wall, #{current_bottle}.".capitalize
  end

  def pass_it_around
    if current_bottle.last_bottle?
      "Go to the store and buy some more,"
    else
      "Take #{current_bottle.take_it_or_one} down and pass it around,"
    end + " #{current_bottle.next_bottle} on the wall."
  end
end

class Bottle
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def to_s
    case number
    when 1; "1 bottle of beer"
    when 0; "no more bottles of beer"
    else
      "#{number} bottles of beer"
    end
  end

  def take_it_or_one
    if number == 1
      "it"
    else
      "one"
    end
  end

  def next_bottle
    if last_bottle?
      Bottle.new(99)
    else
      Bottle.new(number - 1)
    end
  end

  def last_bottle?
    number == 0
  end
end
