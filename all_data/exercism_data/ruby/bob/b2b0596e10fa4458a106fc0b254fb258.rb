class Bob
  def hey(msg)
    msg = Message.new(msg.to_s)

    return 'Fine. Be that way.' if msg.silent?
    return 'Woah, chill out!'   if msg.shouting?
    return 'Sure.'              if msg.asking?
    'Whatever.'
  end
end

class Message < String
  def silent?
    empty?
  end

  def shouting?
    upcase == self
  end

  def asking?
    end_with? '?'
  end
end
