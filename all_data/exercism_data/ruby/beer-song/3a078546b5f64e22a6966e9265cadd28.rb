class Beer

  def self.song
    Beer.new.sing(99)
  end

  def verse verse
    "#{line_one verse}\n#{line_two verse}\n"
  end

  def sing start, finish = 0
    start.downto(finish).inject("") { |result, i| result << "#{verse i}\n" }
  end

  private

  def line_one verse
    "#{bottles(verse).capitalize} of beer on the wall, #{bottles verse} of beer."
  end

  def line_two verse
    if verse != 0
      "Take #{take verse} down and pass it around, #{bottles verse - 1} of beer on the wall."
    else
      "Go to the store and buy some more, 99 bottles of beer on the wall."
    end
  end

  def bottles count
    if count == 0
      "no more bottles"
    elsif count == 1
      "1 bottle"
    else
      "#{count} bottles"
    end
  end

  def take count
    if count == 1
      "it"
    else
      "one"
    end
  end
end

class Fixnum
  def bottles_of_beer
    Beer.new.sing(self)
  end

  def bottles_of consumable
    Beer.new.sing(self).gsub(/\bbeer\b/, consumable)
  end
end

print Beer.song
