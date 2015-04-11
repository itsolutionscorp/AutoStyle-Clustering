class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(start, stop)
    start.downto(stop).map{|number| verse(number) + "\n"}.join
  end

  def verse(number)
    Verse.new(number).to_s
  end
end

class Verse
  attr_reader :number
  def initialize(number)
    @number = number
  end
  
  def to_s
    "#{current_inventory.to_s.capitalize} #{current_container} of beer on the wall, " +
    "#{current_inventory} #{current_container} of beer.\n" +
    "#{action}, " +
    "#{next_inventory} #{next_container} of beer on the wall.\n"
  end
  
  def current_inventory
    if number == 0
      "no more"
    else
      number
    end
  end

  def next_inventory
    if number == 1
      "no more"
    elsif number == 0
      99
    else
      number - 1
    end
  end

  def next_container
    if number == 2
      "bottle"
    else
      "bottles"
    end
  end
  
  def current_container
    if number == 1
      "bottle"
    else
      "bottles"
    end
  end
 
  def action
    if number == 0
      "Go to the store and buy some more"
    else
      "Take #{pronoun} down and pass it around"
    end
  end

  def pronoun
    if number == 1
      "it"
    else
      "one"
    end
  end
end
