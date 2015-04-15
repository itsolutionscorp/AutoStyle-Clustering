class Bob
  def hey(input)
    case
    when say_nothing(input) then 'Fine, be that way.'
    when shout(input) then 'Woah, chill out!'
    when ask_question(input) then 'Sure.'
    when begin_with_bro?(input) then to_leet(input)
    else
      'Whatever.'
    end
  end

  def to_leet(input)
    if begin_with_bro?(input)
      input.gsub(/[epa]/,'e'=>3,'p' =>'P','a'=>4)
    end
  end

  def say_nothing(input)
    input == ""
  end

  def shout(input)
    input == input.upcase
  end

  def ask_question(input)
    input.end_with?("?")
  end

  def begin_with_bro?(input)
    input.start_with?("Bro, ") ? true : false      
  end
end
