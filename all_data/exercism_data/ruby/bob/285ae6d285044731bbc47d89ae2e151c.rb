class Speech
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def silence?
    !message || message.strip.empty?
  end

  def scream?
    message.upcase == message
  end

  def question?
    message.end_with?('?')
  end
end

class Bob
  def hey(message)
    speach = Speech.new(message)
    case
    when speach.silence?
      'Fine. Be that way!'
    when speach.scream?
      'Woah, chill out!'
    when speach.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
