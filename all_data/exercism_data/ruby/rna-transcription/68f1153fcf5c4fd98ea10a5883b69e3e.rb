class Bob
  def hey(message)
    message = Message.new(message.to_s)

    case
      when message.silent?   then 'Fine. Be that way.'
      when message.question? then 'Sure.'
      when message.shouting? then 'Woah, chill out!'
      else                         'Whatever.'
    end
  end
end

class Message < String
  def silent?
    [nil, ''].include?(self)
  end

  def question?
    self.end_with?('?')
  end

  def shouting?
    self == self.upcase
  end
end
