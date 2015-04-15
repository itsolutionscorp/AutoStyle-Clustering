class Bob
  def hey statement
    return silence_response if statement.strip.empty?
    return angry_reponse if statement.upcase == statement
    return answer_response if statement.end_with?('?')
    passive_response
  end

  private
  def angry_reponse
    'Woah, chill out!'
  end

  def passive_response
    'Whatever.'
  end

  def answer_response
    'Sure.'
  end

  def silence_response
    'Fine. Be that way!'
  end
end
