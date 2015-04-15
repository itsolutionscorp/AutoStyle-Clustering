class Beer
  def verse(number)
    Verse.new(number).to_s
  end

  def sing(from, to = 0)
    from.downto(to).map {|nr| verse(nr) + "\n"}.join
  end

  class Verse < Struct.new :number
    def to_s
      if number > 0
        first_part + second_part
      else
        first_part + last_second_part
      end
    end

    private
    def first_part
      "#{bottles.capitalize} of beer on the wall, "\
      "#{bottles} of beer.\n"
    end

    def second_part
      "Take #{reference} down and pass it around, "\
      "#{bottles number - 1} of beer on the wall.\n"
    end

    def last_second_part
      "Go to the store and buy some more, "\
      "99 bottles of beer on the wall.\n"
    end

    def bottles(number = nil)
      case number ||= self.number
      when 0 then "no more bottles"
      when 1 then "1 bottle"
      else "#{number} bottles"
      end
    end

    def reference
      number == 1 ? 'it' : 'one'
    end
  end

end
