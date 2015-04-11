class Bob

  def hey(speech)
    if question_response?(speech)
      'Sure.'
    elsif yelling_response?(speech)
      'Woah, chill out!'
    elsif silence_response?(speech)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def question_response?(speech)
    speech[-1] == "?" && speech != speech.upcase || speech[-1] == "?" && !speech.match(/[A-Za-z]+/)
  end

  def yelling_response?(speech)
    speech == speech.upcase && speech.match(/[A-Za-z]+/)
  end

  def silence_response?(speech)
    speech.match(/\W+/) && !speech.match(/\w+/) || speech.empty?
  end

end
