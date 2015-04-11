require 'delegate'

class Message < SimpleDelegator
  def initialize(text)
    super(text.to_s)
  end

  def silence?
    empty?
  end

  def shouting?
    all_caps?
  end

  def question?
    end_with? '?'
  end

  def all_caps?
    upcase == self
  end
end

class Bob
  def hey(message)
    message = Message.new(message)

    return 'Fine. Be that way!' if message.silence?
    return 'Woah, chill out!' if message.shouting?

    if message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
