class Beer

  def self.song
    Beer.new.sing(99)
  end

  def verse verse
    "#{line_one verse}\n#{line_two verse}\n"
  end

  def sing start, finish = 0
    (finish..start).inject("") { |result, i| result = "#{verse i}\n#{result}" }
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

  def bottles verse
    if verse == 0
      "no more bottles"
    elsif verse == 1
      "1 bottle"
    else
      "#{verse} bottles"
    end
  end

  def take verse
    if verse == 1
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
    # I would probably prefer to generalize the Beer class as Consumable, but I am
    #   leaving this way for now.  I can (and will) refactor it if it is
    #   nitpick worthy
    Beer.new.sing(self).gsub(/\bbeer\b/, consumable)
  end
end

# Is there a way to submit multiple files, or are the extensions intended to be done
#   separately on our own?  All this down here is weird.
print Beer.song
