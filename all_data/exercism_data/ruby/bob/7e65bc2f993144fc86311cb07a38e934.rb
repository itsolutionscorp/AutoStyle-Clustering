class Bob
  def hey statement
    return silence_response if is_empty? statement
    return angry_reponse if is_yelling? statement
    return answer_response if is_question? statement
    passive_response
  end

  private
  def is_empty? statement
    statement.strip.empty?
  end

  def is_yelling? statement
    statement.upcase == statement
  end

  def is_question? statement
    statement.end_with?('?')
  end

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
