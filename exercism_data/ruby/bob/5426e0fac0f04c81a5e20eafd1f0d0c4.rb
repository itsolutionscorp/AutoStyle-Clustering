class Bob
  def initialize
    @responders = [ShoutResponse.new, StatementResponse.new, QuestionResponse.new, QuietResponse.new]
  end

  def hey(message)
    @responders.find { |responder| responder.hears?(message.to_s) }.reply
  end
end

class BobResponse
  def hears?(message)
    raise "Implement hears? in inheriting class"
  end

  def reply
    raise "Implement reply in inheriting class"
  end
end

class ShoutResponse < BobResponse
  def hears?(message)
    message.length > 0 && message.upcase == message
  end

  def reply
    'Woah, chill out!'
  end
end

class StatementResponse < BobResponse
  def hears?(message)
    message =~ /[\.!]$/
  end

  def reply
    'Whatever.'
  end
end

class QuestionResponse < BobResponse
  def hears?(message)
    message =~ /\?$/
  end

  def reply
    'Sure.'
  end
end

class QuietResponse < BobResponse
  def hears?(message)
    message.length == 0
  end

  def reply
    'Fine. Be that way!'
  end
end
