class Bob
  def hey(message)
    tone = Interpreter.new(message)
    case
    when tone.vague?
      'Fine. Be that way.'
    when tone.questioning?
      'Sure.'
    when tone.exlaiming?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class Interpreter
  attr_reader :message

  def initialize(message)
    @message = String(message)
  end

  def vague?
    message.empty?
  end

  def questioning?
    message.end_with?("?")
  end

  def exlaiming?
    message == message.upcase
  end
end
