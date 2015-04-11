class Bob
  def hey(message)
    respond_to(message)
  end

  private
  def respond_to(message)
    specific_response(message) || indifference
  end

  def specific_response(message)
    response_type = responses.detect do |check, response_type|
      send(check, message.to_s)
    end
    response_type[1] if response_type
  end

  def responses
    {
      :silence?  => sulkiness,
      :yelling?  => indignance,
      :question? => unhelpfulness
    }
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message =~ /\?\Z/
  end

  def silence?(message)
    message == ""
  end

  def indifference
    "Whatever."
  end

  def indignance
    "Woah, chill out!"
  end

  def unhelpfulness
    "Sure."
  end

  def sulkiness
    "Fine. Be that way!"
  end
end
