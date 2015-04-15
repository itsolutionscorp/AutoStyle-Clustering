class Message
  attr_accessor :body

  def initialize(body)
    self.body = body
  end

  def shouting?
    present? && body == body.upcase
  end

  def asking?
    present? && body.end_with?('?')
  end

  def silent?
    ! present?
  end

  def present?
    body && ! body.empty?
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
      when message.shouting?
        'Woah, chill out!'
      when message.asking?
        'Sure.'
      when message.silent?
        'Fine. Be that way.'
      else
        'Whatever.'
    end
  end
end
