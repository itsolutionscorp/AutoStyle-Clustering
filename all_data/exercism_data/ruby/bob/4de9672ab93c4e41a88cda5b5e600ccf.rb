class Bob
  def hey(input)
    case
    when silent_treatment?(input) then 'Fine, be that way.'
    when shouting?(input) then 'Woah, chill out!'
    when question?(input) then 'Sure.'
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

  def silent_treatment?(input)
    input == ""
  end

  def shouting?(input)
    input == input.upcase
  end

  def question?(input)
    input.end_with?("?")
  end

  def begin_with_bro?(input)
    input.start_with?("Bro, ")      
  end
end
