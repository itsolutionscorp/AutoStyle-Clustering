class TeenageBrain

  def initialize(message)
    @message = message.to_s
  end

  def process
    if silence?
      :silent
    elsif shouting?
      :shouting
    elsif question?
      :question
    else
      :statement
    end
  end

  private
  def silence?
    @message.empty?
  end

  def shouting?
    @message.upcase == @message
  end

  def question?
    @message.end_with?('?')
  end
end

class Bob
  RESPONSES = {
    silent: 'Fine. Be that way!',
    question: 'Sure.',
    statement: 'Whatever.',
    shouting: 'Woah, chill out!'
  }

  def hey(message)
    respond(TeenageBrain.new(message).process)
  end

  private
  def respond(response_type)
    RESPONSES[response_type]
  end
end
