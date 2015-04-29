require 'delegate'

class Message < SimpleDelegator
  def initialize(text)
    super(text.to_s)
  end

  def silence?
    empty?
  end

  def shouting?
    all_caps? and not silence?
  end

  def question?
    end_with? '?' and not shouting?
  end

  def all_caps?
    upcase == __getobj__
  end
end

class Bob
  def hey(message)
    message = Message.new(message)

    if message.silence?
      'Fine. Be that way!'
    elsif message.question?
      'Sure.'
    elsif message.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
