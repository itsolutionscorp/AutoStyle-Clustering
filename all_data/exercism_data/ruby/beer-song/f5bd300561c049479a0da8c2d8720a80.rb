class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(start, stop)
    start.downto(stop).map{|number| verse(number) + "\n"}.join
  end

  def verse(number)
    Verse.for(number).to_s
  end
end

class Verse
  def self.for(number)
    case number
    when 0 then Verse0
    when 1 then Verse1
    when 2 then Verse2
    else Verse
    end.new(number)
  end

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
  
  def current_inventory; number end
  def next_inventory; number - 1 end
  def current_container; "bottles" end
  def next_container; "bottles" end
  def action; "Take #{pronoun} down and pass it around" end
  def pronoun; "one" end
end

class Verse0 < Verse
  def current_inventory; "no more" end
  def next_inventory; 99 end
  def action; "Go to the store and buy some more" end
end

class Verse1 < Verse
  def next_inventory; "no more" end
  def current_container; "bottle" end
  def pronoun; "it" end
end

class Verse2 < Verse
  def next_container; "bottle" end
end
