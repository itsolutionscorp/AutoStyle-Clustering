class Inputs
  attr_accessor :response
end

class ShoutingInput < Inputs
  def initialize
    @response = 'Woah, chill out!'
  end
end

class StatementInput < Inputs
  def initialize
    @response = 'Whatever.'
  end
end

class QuestionInput < Inputs
  def initialize
    @response = 'Sure.'
  end
end

class SilentInput < Inputs
  def initialize
    @response = 'Fine. Be that way!'
  end
end

class TeenageBrain
  def initialize(message)
    @message = message
  end

  def process
    if silence?
      SilentInput.new().response
    elsif all_caps?
      ShoutingInput.new().response
    elsif ends_in_question_mark?
      QuestionInput.new().response
    else
      StatementInput.new().response
    end
  end

  private
  def silence?
    @message.nil? || @message == ''
  end

  def all_caps?
    @message.upcase == @message
  end

  def ends_in_question_mark?
    @message.slice(-1,1) == '?'
  end
end

class Bob
  def hey(message)
    response = TeenageBrain.new(message).process
    response
  end
end
