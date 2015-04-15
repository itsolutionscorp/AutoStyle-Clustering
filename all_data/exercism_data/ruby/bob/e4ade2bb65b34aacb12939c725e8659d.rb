require 'delegate'

class Message < DelegateClass(String)
  def initialize message
    super(message.to_s)
  end

  alias silent? empty?

  def yelling?
    self == upcase
  end

  def question?
    end_with? ??
  end
end

class Bob
  def hey message
    message = Message.new(message)
    case
    when message.silent?
      'Fine. Be that way!'
    when message.yelling?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
