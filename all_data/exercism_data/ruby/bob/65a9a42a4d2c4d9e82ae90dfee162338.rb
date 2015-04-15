class TeenageBrain
  SILENT_RESPONSE = 'Fine. Be that way!'
  QUESTION_RESPONSE = 'Sure.'
  STATEMENT_RESPONSE = 'Whatever.'
  SHOUTING_RESPONSE = 'Woah, chill out!'

  def initialize(message)
    @message = message.to_s
  end

  def process
    if silence?
      SILENT_RESPONSE
    elsif shouting?
      SHOUTING_RESPONSE
    elsif question?
      QUESTION_RESPONSE
    else
      STATEMENT_RESPONSE
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
  def hey(message)
    TeenageBrain.new(message).process
  end
end
