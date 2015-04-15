class NothingResponse
  def response
    'Fine, be that way.'
  end
end

class YellingResponse
  def response
    "Woah, chill out!"
  end
end

class DefaultResponse
  def response
    "Whatever."
  end
end

class QuestionResponse
  def response
    "Sure."
  end
end

module Response
  def respond(message)
    response = if nothing?(message)
      NothingResponse.new
    elsif yelling?(message)
      YellingResponse.new
    elsif question?(message)
      QuestionResponse.new
    else
      DefaultResponse.new
    end

    response.response

  end

  def nothing?(message)
    message.empty?
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message[-1] == '?'
  end

end

class Bob
  include Response

  def hey(message)
    respond(message)
  end

end
