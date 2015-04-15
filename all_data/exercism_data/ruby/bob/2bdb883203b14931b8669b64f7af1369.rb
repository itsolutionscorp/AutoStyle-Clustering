class Inputs
  attr_accessor :severity, :response
end

class ShoutingInput < Inputs
  def initialize(severity)
    @severity = severity
    @response = 'Woah, chill out!'
  end
end

class StatementInput < Inputs
  def initialize(severity)
    @severity = severity
    @response = 'Whatever.'
  end
end

class QuestionInput < Inputs
  def initialize(severity)
    @severity = severity
    @response = 'Sure.'
  end
end

class SilentInput < Inputs
  def initialize(severity)
    @severity = severity
    @response = 'Fine. Be that way!'
  end
end

class TeenageBrain
  def initialize(message)
    @message = message
  end

  def process
    if @message.nil? || @message == ''
      SilentInput.new(2).response
    elsif @message.upcase == @message
      ShoutingInput.new(9).response
    elsif @message.slice(-1,1) == '?'
      QuestionInput.new(2).response
    else
      StatementInput.new(2).response
    end
  end
end

class Bob
  def hey(message)
    response = TeenageBrain.new(message).process
    response
  end
end
