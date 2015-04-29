class Beer
  def sing(number, to = 0)
    song = ""
    number.downto(to) { |i| song << verse(i) << "\n" }
    song
  end

  def verse(number)
    first_line_in_verse(number) + second_line_in_verse(number)
  end

  private
    def bottles_string(number_of_bottles, beginning_of_line = false)
      case number_of_bottles
      when -1
        "99 bottles"
      when 0
        first_letter = "n"
        first_letter.upcase! if beginning_of_line
        "#{first_letter}o more bottles"
      when 1
        "1 bottle"
      else
        "#{number_of_bottles} bottles"
      end
    end

    def action_string(number_of_bottles)
      take_down = nil
      case number_of_bottles
      when 0
        action_string = "Go to the store and buy some more"
      when 1
        take_down = "it"
      else
        take_down = "one"
      end
      action_string ||= "Take #{take_down} down and pass it around" unless take_down.nil?
      action_string
    end

    def first_line_in_verse(number)
      "#{bottles_string(number, true)} of beer on the wall, #{bottles_string(number)} of beer.\n"
    end

    def second_line_in_verse(number)
      "#{action_string(number)}, #{bottles_string(number - 1)} of beer on the wall.\n"
    end
end
