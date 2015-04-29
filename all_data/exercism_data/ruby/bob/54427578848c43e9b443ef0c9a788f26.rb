class Bob
  def hey(message)
    BobResponder.new(message).respond
  end
end

class BobResponder
  def initialize(message)
    @message = message
  end

  def respond
    case
    when silence?
      "Fine. Be that way!"
    when yelling?
      'Woah, chill out!'
    when question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?
    blank?
  end

  def question?
    @message.end_with?('?')
  end

  def yelling?
    @message.upcase == @message
  end

  def blank?
    @message.nil? || @message == ""
  end
end
