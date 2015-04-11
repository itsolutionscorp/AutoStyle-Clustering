class Bob
  def hey(sentance)
    case sentance
    when matches_silence? then response_to_silence
    when matches_shouting?(sentance) then response_to_shouting
    when matches_talking_forcefully? then response_to_talking_forcefully
    when matches_asking_a_question? then response_to_question
    when matches_statement? then response_to_statement
    end
  end

  private

  def matches_silence?
    /\A\s*\Z/
  end

  def matches_shouting?(sentance)
    sentance.upcase
  end

  def matches_talking_forcefully?
    /!\Z/
  end

  def matches_asking_a_question?
    /\?\Z/
  end

  def matches_statement?
    /\W+/
  end

  def response_to_silence
    'Fine. Be that way!'
  end

  def response_to_shouting
    'Woah, chill out!'
  end

  def response_to_talking_forcefully
    'Whatever.'
  end

  def response_to_question
    'Sure.'
  end

  def response_to_statement
    'Whatever.'
  end

end
