class Bob

  def hey(message)
    @message = message
    my_response = typical_teenage_response

    if they_said_something?
      my_response = whoa_chill if they_are_shouting?
      my_response = dont_really_care if its_a_question?
    else
      my_response = wasting_my_time
    end

    return my_response
  end

private

  def typical_teenage_response
    'Whatever.'
  end

  def whoa_chill
    'Woah, chill out!'
  end

  def dont_really_care
    'Sure.'
  end

  def wasting_my_time
    'Fine. Be that way.'
  end
  def they_are_shouting?
    @message && @message == @message.upcase
  end

  def its_a_question?
    @message && @message[-1] == '?'
  end

  def they_said_something?
    @message && @message.strip.length > 0
  end

end
