class Teenager
  def responds_to_silence
    'Fine. Be that way!'
  end

  def responds_to_shouting
    'Woah, chill out!'    
  end

  def responds_to_questions_or_prattles
    'Sure.'    
  end

  def default_response
    'Whatever.'
  end
end

class Bob < Teenager
  def hey(message)
    @message = message

    if silent
      responds_to_silence
    elsif shouting_or_foreceful
      responds_to_shouting
    elsif questioning_or_prattling
      responds_to_questions_or_prattles
    else
      default_response
    end
  end

  def silent
    @message.to_s == ''
  end

  def shouting_or_foreceful 
    @message == @message.upcase
  end

  def questioning_or_prattling 
    @message.end_with?("?")
  end
end
