class Message < String
  def initialize(string)
    replace string || ''
  end

  def shouting?
    self == upcase
  end

  def asking?
    end_with?('?')
  end

  def silent?
    empty?
  end
end

class Bob
  def hey(message)
    message = Message.new(message)

    respond(message)
  end

  private
  def respond(message)
    case
      when message.silent?
        'Fine. Be that way.'
      when message.shouting?
        'Woah, chill out!'
      when message.asking?
        'Sure.'
      else
        'Whatever.'
    end
  end
end
