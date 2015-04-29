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
    elsif all_caps?
      SHOUTING_RESPONSE
    elsif ends_in_question_mark?
      QUESTION_RESPONSE
    else
      STATEMENT_RESPONSE
    end
  end

  private
  def silence?
    @message.empty?
  end

  def all_caps?
    @message.upcase == @message
  end

  def ends_in_question_mark?
    @message.end_with?('?')
  end
end

class Bob
  def hey(message)
    TeenageBrain.new(message).process
  end
end
