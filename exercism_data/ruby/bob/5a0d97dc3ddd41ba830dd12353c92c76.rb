# exercism - ruby/bob
class Bob
  def hey(message)
    @message = message.chomp
    return 'Fine. Be that way!' if silent
    return 'Woah, chill out!'   if shouty
    return 'Sure.'              if asking
    'Whatever.'
  end

  def silent
    @message.strip == ''
  end

  def shouty
    @message.has_alpha? and @message == @message.upcase 
  end

  def asking
    @message.end_with? '?'
  end
end

class String
  def has_alpha?
    !!self.match(/[A-Z]/)
  end
end
