module LackadaisicalTeenager
  def respond(message)
    nothing?(message) || yelling?(message) || question?(message) || default_response
  end

  def nothing?(message)
    respond_to_nothing if message.empty?
  end

  def yelling?(message)
    respond_to_yelling if message.upcase == message
  end

  def question?(message)
    respond_to_question if message.end_with?('?')
  end

  def respond_to_nothing
    'Fine. Be that way.'
  end

  def respond_to_yelling
    "Woah, chill out!"
  end

  def respond_to_question
    "Sure."
  end

  def default_response
    "Whatever."
  end

end

class Bob
  include LackadaisicalTeenager

  def hey(message)
    respond(message)
  end

end
