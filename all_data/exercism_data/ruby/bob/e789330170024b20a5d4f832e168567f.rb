class Bob
  def hey(message)
    response(message)
  end

  def response(message)
    tone = Interpreter.new(message)

    case
    when tone.is_statement?
      'Whatever.'
    when tone.is_question?
      'Sure.'
    when tone.is_exlamation?
      'Woah, chill out!'
    else
      'Fine. Be that way.'
    end
  end
end

class Interpreter
  def initialize(message)
    @message = message
  end

  def vague?
    @message.nil? || @message.empty?
  end

  def is_statement?
    !vague? && !is_question? && !is_exlamation?
  end

  def is_question?
    !vague? && @message.end_with?("?")
  end

  def is_exlamation?
    !vague? && @message == @message.upcase
  end
end
