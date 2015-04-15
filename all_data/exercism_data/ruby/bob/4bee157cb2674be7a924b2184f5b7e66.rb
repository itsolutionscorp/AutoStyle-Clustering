class Interpretation
  def initialize message
    @message = message
  end

  def is_forceful?
    @message.match(/[A-Z]/) && !@message.match(/[a-z]/)
  end

  def is_question?
    @message.end_with? '?'
  end

  def is_silence?
    @message.strip.empty?
  end

  def self.of message
    Interpretation.new message
  end
end

class Bob
  def hey message
    content = Interpretation.of message

    case
    when content.is_forceful?
      'Woah, chill out!'
    when content.is_question?
      'Sure.'
    when content.is_silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
